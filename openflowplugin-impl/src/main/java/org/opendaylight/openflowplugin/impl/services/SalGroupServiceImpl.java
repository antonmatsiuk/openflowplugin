/**
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.openflowplugin.impl.services;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Future;
import javax.annotation.Nullable;
import org.opendaylight.openflowplugin.api.openflow.device.DeviceContext;
import org.opendaylight.openflowplugin.api.openflow.device.RequestContextStack;
import org.opendaylight.openflowplugin.api.openflow.rpc.ItemLifeCycleSource;
import org.opendaylight.openflowplugin.api.openflow.rpc.listener.ItemLifecycleListener;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.AddGroupInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.AddGroupOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.RemoveGroupInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.RemoveGroupOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.SalGroupService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.UpdateGroupInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.service.rev130918.UpdateGroupOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.Group;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.GroupId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.GroupBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.GroupKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yangtools.yang.binding.KeyedInstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalGroupServiceImpl implements SalGroupService, ItemLifeCycleSource {
    private static final Logger LOG = LoggerFactory.getLogger(SalGroupServiceImpl.class);
    private final GroupService<AddGroupInput, AddGroupOutput> addGroup;
    private final GroupService<Group, UpdateGroupOutput> updateGroup;
    private final GroupService<RemoveGroupInput, RemoveGroupOutput> removeGroup;
    private final DeviceContext deviceContext;
    private ItemLifecycleListener itemLifecycleListener;

    public SalGroupServiceImpl(final RequestContextStack requestContextStack, final DeviceContext deviceContext) {
        this.deviceContext = deviceContext;
        addGroup = new GroupService<>(requestContextStack, deviceContext, AddGroupOutput.class);
        updateGroup = new GroupService<>(requestContextStack, deviceContext, UpdateGroupOutput.class);
        removeGroup = new GroupService<>(requestContextStack, deviceContext, RemoveGroupOutput.class);
    }

    @Override
    public void setItemLifecycleListener(@Nullable ItemLifecycleListener itemLifecycleListener) {
        this.itemLifecycleListener = itemLifecycleListener;
    }

    @Override
    public Future<RpcResult<AddGroupOutput>> addGroup(final AddGroupInput input) {
        deviceContext.getDeviceGroupRegistry().store(input.getGroupId());
        final ListenableFuture<RpcResult<AddGroupOutput>> resultFuture = addGroup.handleServiceCall(input);
        Futures.addCallback(resultFuture, new FutureCallback<RpcResult<AddGroupOutput>>() {
            @Override
            public void onSuccess(RpcResult<AddGroupOutput> result) {
                if (result.isSuccessful()) {
                    LOG.debug("group add finished without error, id={}", input.getGroupId().getValue());
                    addIfNecessaryToDS(input.getGroupId(), input);
                } else {
                    deviceContext.getDeviceGroupRegistry().markToBeremoved(input.getGroupId());
                    LOG.debug("group add with id={} failed, errors={}", input.getGroupId().getValue(), result.getErrors());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                deviceContext.getDeviceGroupRegistry().markToBeremoved(input.getGroupId());
                LOG.error("Service call for adding groups failed, id={}. Exception: {}", input.getGroupId().getValue(), t);
            }
        });

        return resultFuture;
    }


    @Override
    public Future<RpcResult<UpdateGroupOutput>> updateGroup(final UpdateGroupInput input) {
        final ListenableFuture<RpcResult<UpdateGroupOutput>> resultFuture = updateGroup.handleServiceCall(input.getUpdatedGroup());
        Futures.addCallback(resultFuture, new FutureCallback<RpcResult<UpdateGroupOutput>>() {
            @Override
            public void onSuccess(@Nullable RpcResult<UpdateGroupOutput> result) {
                if (result.isSuccessful()) {
                    LOG.debug("Group update succeded");
                    removeIfNecessaryFromDS(input.getOriginalGroup().getGroupId());
                    addIfNecessaryToDS(input.getUpdatedGroup().getGroupId(), input.getUpdatedGroup());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                LOG.debug("Group update failed for id={}. Exception: {}", input.getOriginalGroup().getGroupId(), t);
            }
        });
        return resultFuture;
    }

    @Override
    public Future<RpcResult<RemoveGroupOutput>> removeGroup(final RemoveGroupInput input) {
        removeGroup.getDeviceContext().getDeviceGroupRegistry().markToBeremoved(input.getGroupId());
        final ListenableFuture<RpcResult<RemoveGroupOutput>> resultFuture = removeGroup.handleServiceCall(input);
        Futures.addCallback(resultFuture, new FutureCallback<RpcResult<RemoveGroupOutput>>() {
            @Override
            public void onSuccess(@Nullable RpcResult<RemoveGroupOutput> result) {
                if (result.isSuccessful()) {
                    LOG.debug("Group remove succeded");
                    removeIfNecessaryFromDS(input.getGroupId());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                LOG.error("Group remove failed for id={}. Exception: {}", input.getGroupId(), t);
            }
        });
        return resultFuture;
    }

    private void removeIfNecessaryFromDS(final GroupId groupId) {
        if (itemLifecycleListener != null) {
            KeyedInstanceIdentifier<org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.Group, GroupKey> groupPath
                    = createGroupPath(groupId,
                    deviceContext.getDeviceState().getNodeInstanceIdentifier());
            itemLifecycleListener.onRemoved(groupPath);
        }
    }

    private void addIfNecessaryToDS(final GroupId groupId, final Group data) {
        if (itemLifecycleListener != null) {
            KeyedInstanceIdentifier<org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.Group, GroupKey> groupPath
                    = createGroupPath(groupId,
                    deviceContext.getDeviceState().getNodeInstanceIdentifier());
            itemLifecycleListener.onAdded(groupPath, new GroupBuilder(data).build());
        }
    }

    static KeyedInstanceIdentifier<org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.Group, GroupKey> createGroupPath(final GroupId groupId, final KeyedInstanceIdentifier<Node, NodeKey> nodePath) {
        return nodePath.augmentation(FlowCapableNode.class).child(org.opendaylight.yang.gen.v1.urn.opendaylight.group.types.rev131018.groups.Group.class, new GroupKey(groupId));
    }
}
