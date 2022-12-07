// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package generalnet.generalNet


object GeneralnetGrpc {
  val METHOD_CONNECT2SERVER: _root_.io.grpc.MethodDescriptor[generalnet.generalNet.Connect2ServerRequest, generalnet.generalNet.Connect2ServerResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("generalnet.Generalnet", "connect2Server"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[generalnet.generalNet.Connect2ServerRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[generalnet.generalNet.Connect2ServerResponse])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(generalnet.generalNet.GeneralNetProto.javaDescriptor.getServices().get(0).getMethods().get(0)))
      .build()
  
  val METHOD_SORT_END_MSG2MASTER: _root_.io.grpc.MethodDescriptor[generalnet.generalNet.SortEndMsg2MasterRequest, generalnet.generalNet.SortEndMsg2MasterResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("generalnet.Generalnet", "sortEndMsg2Master"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[generalnet.generalNet.SortEndMsg2MasterRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[generalnet.generalNet.SortEndMsg2MasterResponse])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(generalnet.generalNet.GeneralNetProto.javaDescriptor.getServices().get(0).getMethods().get(1)))
      .build()
  
  val METHOD_SAMPLING_END_MSG2MASTER: _root_.io.grpc.MethodDescriptor[generalnet.generalNet.SamplingEndMsg2MasterRequest, generalnet.generalNet.SamplingEndMsg2MasterResponse] =
    _root_.io.grpc.MethodDescriptor.newBuilder()
      .setType(_root_.io.grpc.MethodDescriptor.MethodType.UNARY)
      .setFullMethodName(_root_.io.grpc.MethodDescriptor.generateFullMethodName("generalnet.Generalnet", "samplingEndMsg2Master"))
      .setSampledToLocalTracing(true)
      .setRequestMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[generalnet.generalNet.SamplingEndMsg2MasterRequest])
      .setResponseMarshaller(_root_.scalapb.grpc.Marshaller.forMessage[generalnet.generalNet.SamplingEndMsg2MasterResponse])
      .setSchemaDescriptor(_root_.scalapb.grpc.ConcreteProtoMethodDescriptorSupplier.fromMethodDescriptor(generalnet.generalNet.GeneralNetProto.javaDescriptor.getServices().get(0).getMethods().get(2)))
      .build()
  
  val SERVICE: _root_.io.grpc.ServiceDescriptor =
    _root_.io.grpc.ServiceDescriptor.newBuilder("generalnet.Generalnet")
      .setSchemaDescriptor(new _root_.scalapb.grpc.ConcreteProtoFileDescriptorSupplier(generalnet.generalNet.GeneralNetProto.javaDescriptor))
      .addMethod(METHOD_CONNECT2SERVER)
      .addMethod(METHOD_SORT_END_MSG2MASTER)
      .addMethod(METHOD_SAMPLING_END_MSG2MASTER)
      .build()
  
  trait Generalnet extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = Generalnet
    def connect2Server(request: generalnet.generalNet.Connect2ServerRequest): scala.concurrent.Future[generalnet.generalNet.Connect2ServerResponse]
    def sortEndMsg2Master(request: generalnet.generalNet.SortEndMsg2MasterRequest): scala.concurrent.Future[generalnet.generalNet.SortEndMsg2MasterResponse]
    def samplingEndMsg2Master(request: generalnet.generalNet.SamplingEndMsg2MasterRequest): scala.concurrent.Future[generalnet.generalNet.SamplingEndMsg2MasterResponse]
  }
  
  object Generalnet extends _root_.scalapb.grpc.ServiceCompanion[Generalnet] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[Generalnet] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = generalnet.generalNet.GeneralNetProto.javaDescriptor.getServices().get(0)
    def scalaDescriptor: _root_.scalapb.descriptors.ServiceDescriptor = generalnet.generalNet.GeneralNetProto.scalaDescriptor.services(0)
    def bindService(serviceImpl: Generalnet, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition =
      _root_.io.grpc.ServerServiceDefinition.builder(SERVICE)
      .addMethod(
        METHOD_CONNECT2SERVER,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[generalnet.generalNet.Connect2ServerRequest, generalnet.generalNet.Connect2ServerResponse] {
          override def invoke(request: generalnet.generalNet.Connect2ServerRequest, observer: _root_.io.grpc.stub.StreamObserver[generalnet.generalNet.Connect2ServerResponse]): _root_.scala.Unit =
            serviceImpl.connect2Server(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .addMethod(
        METHOD_SORT_END_MSG2MASTER,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[generalnet.generalNet.SortEndMsg2MasterRequest, generalnet.generalNet.SortEndMsg2MasterResponse] {
          override def invoke(request: generalnet.generalNet.SortEndMsg2MasterRequest, observer: _root_.io.grpc.stub.StreamObserver[generalnet.generalNet.SortEndMsg2MasterResponse]): _root_.scala.Unit =
            serviceImpl.sortEndMsg2Master(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .addMethod(
        METHOD_SAMPLING_END_MSG2MASTER,
        _root_.io.grpc.stub.ServerCalls.asyncUnaryCall(new _root_.io.grpc.stub.ServerCalls.UnaryMethod[generalnet.generalNet.SamplingEndMsg2MasterRequest, generalnet.generalNet.SamplingEndMsg2MasterResponse] {
          override def invoke(request: generalnet.generalNet.SamplingEndMsg2MasterRequest, observer: _root_.io.grpc.stub.StreamObserver[generalnet.generalNet.SamplingEndMsg2MasterResponse]): _root_.scala.Unit =
            serviceImpl.samplingEndMsg2Master(request).onComplete(scalapb.grpc.Grpc.completeObserver(observer))(
              executionContext)
        }))
      .build()
  }
  
  trait GeneralnetBlockingClient {
    def serviceCompanion = Generalnet
    def connect2Server(request: generalnet.generalNet.Connect2ServerRequest): generalnet.generalNet.Connect2ServerResponse
    def sortEndMsg2Master(request: generalnet.generalNet.SortEndMsg2MasterRequest): generalnet.generalNet.SortEndMsg2MasterResponse
    def samplingEndMsg2Master(request: generalnet.generalNet.SamplingEndMsg2MasterRequest): generalnet.generalNet.SamplingEndMsg2MasterResponse
  }
  
  class GeneralnetBlockingStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[GeneralnetBlockingStub](channel, options) with GeneralnetBlockingClient {
    override def connect2Server(request: generalnet.generalNet.Connect2ServerRequest): generalnet.generalNet.Connect2ServerResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_CONNECT2SERVER, options, request)
    }
    
    override def sortEndMsg2Master(request: generalnet.generalNet.SortEndMsg2MasterRequest): generalnet.generalNet.SortEndMsg2MasterResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_SORT_END_MSG2MASTER, options, request)
    }
    
    override def samplingEndMsg2Master(request: generalnet.generalNet.SamplingEndMsg2MasterRequest): generalnet.generalNet.SamplingEndMsg2MasterResponse = {
      _root_.scalapb.grpc.ClientCalls.blockingUnaryCall(channel, METHOD_SAMPLING_END_MSG2MASTER, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): GeneralnetBlockingStub = new GeneralnetBlockingStub(channel, options)
  }
  
  class GeneralnetStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions = _root_.io.grpc.CallOptions.DEFAULT) extends _root_.io.grpc.stub.AbstractStub[GeneralnetStub](channel, options) with Generalnet {
    override def connect2Server(request: generalnet.generalNet.Connect2ServerRequest): scala.concurrent.Future[generalnet.generalNet.Connect2ServerResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_CONNECT2SERVER, options, request)
    }
    
    override def sortEndMsg2Master(request: generalnet.generalNet.SortEndMsg2MasterRequest): scala.concurrent.Future[generalnet.generalNet.SortEndMsg2MasterResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_SORT_END_MSG2MASTER, options, request)
    }
    
    override def samplingEndMsg2Master(request: generalnet.generalNet.SamplingEndMsg2MasterRequest): scala.concurrent.Future[generalnet.generalNet.SamplingEndMsg2MasterResponse] = {
      _root_.scalapb.grpc.ClientCalls.asyncUnaryCall(channel, METHOD_SAMPLING_END_MSG2MASTER, options, request)
    }
    
    override def build(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): GeneralnetStub = new GeneralnetStub(channel, options)
  }
  
  object GeneralnetStub extends _root_.io.grpc.stub.AbstractStub.StubFactory[GeneralnetStub] {
    override def newStub(channel: _root_.io.grpc.Channel, options: _root_.io.grpc.CallOptions): GeneralnetStub = new GeneralnetStub(channel, options)
    
    implicit val stubFactory: _root_.io.grpc.stub.AbstractStub.StubFactory[GeneralnetStub] = this
  }
  
  def bindService(serviceImpl: Generalnet, executionContext: scala.concurrent.ExecutionContext): _root_.io.grpc.ServerServiceDefinition = Generalnet.bindService(serviceImpl, executionContext)
  
  def blockingStub(channel: _root_.io.grpc.Channel): GeneralnetBlockingStub = new GeneralnetBlockingStub(channel)
  
  def stub(channel: _root_.io.grpc.Channel): GeneralnetStub = new GeneralnetStub(channel)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = generalnet.generalNet.GeneralNetProto.javaDescriptor.getServices().get(0)
  
}