package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: commander.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CommanderGrpc {

  private CommanderGrpc() {}

  public static final java.lang.String SERVICE_NAME = "Commander";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.CommandRequest,
      generated.CommandResponse> getProcessCommandMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "processCommand",
      requestType = generated.CommandRequest.class,
      responseType = generated.CommandResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.CommandRequest,
      generated.CommandResponse> getProcessCommandMethod() {
    io.grpc.MethodDescriptor<generated.CommandRequest, generated.CommandResponse> getProcessCommandMethod;
    if ((getProcessCommandMethod = CommanderGrpc.getProcessCommandMethod) == null) {
      synchronized (CommanderGrpc.class) {
        if ((getProcessCommandMethod = CommanderGrpc.getProcessCommandMethod) == null) {
          CommanderGrpc.getProcessCommandMethod = getProcessCommandMethod =
              io.grpc.MethodDescriptor.<generated.CommandRequest, generated.CommandResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "processCommand"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.CommandRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.CommandResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CommanderMethodDescriptorSupplier("processCommand"))
              .build();
        }
      }
    }
    return getProcessCommandMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CommanderStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommanderStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommanderStub>() {
        @java.lang.Override
        public CommanderStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommanderStub(channel, callOptions);
        }
      };
    return CommanderStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CommanderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommanderBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommanderBlockingStub>() {
        @java.lang.Override
        public CommanderBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommanderBlockingStub(channel, callOptions);
        }
      };
    return CommanderBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CommanderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CommanderFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CommanderFutureStub>() {
        @java.lang.Override
        public CommanderFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CommanderFutureStub(channel, callOptions);
        }
      };
    return CommanderFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void processCommand(generated.CommandRequest request,
        io.grpc.stub.StreamObserver<generated.CommandResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getProcessCommandMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Commander.
   */
  public static abstract class CommanderImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CommanderGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Commander.
   */
  public static final class CommanderStub
      extends io.grpc.stub.AbstractAsyncStub<CommanderStub> {
    private CommanderStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommanderStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommanderStub(channel, callOptions);
    }

    /**
     */
    public void processCommand(generated.CommandRequest request,
        io.grpc.stub.StreamObserver<generated.CommandResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getProcessCommandMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Commander.
   */
  public static final class CommanderBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CommanderBlockingStub> {
    private CommanderBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommanderBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommanderBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.CommandResponse processCommand(generated.CommandRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getProcessCommandMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Commander.
   */
  public static final class CommanderFutureStub
      extends io.grpc.stub.AbstractFutureStub<CommanderFutureStub> {
    private CommanderFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CommanderFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CommanderFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.CommandResponse> processCommand(
        generated.CommandRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getProcessCommandMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PROCESS_COMMAND = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PROCESS_COMMAND:
          serviceImpl.processCommand((generated.CommandRequest) request,
              (io.grpc.stub.StreamObserver<generated.CommandResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getProcessCommandMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generated.CommandRequest,
              generated.CommandResponse>(
                service, METHODID_PROCESS_COMMAND)))
        .build();
  }

  private static abstract class CommanderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CommanderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.CommanderOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Commander");
    }
  }

  private static final class CommanderFileDescriptorSupplier
      extends CommanderBaseDescriptorSupplier {
    CommanderFileDescriptorSupplier() {}
  }

  private static final class CommanderMethodDescriptorSupplier
      extends CommanderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CommanderMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CommanderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CommanderFileDescriptorSupplier())
              .addMethod(getProcessCommandMethod())
              .build();
        }
      }
    }
    return result;
  }
}
