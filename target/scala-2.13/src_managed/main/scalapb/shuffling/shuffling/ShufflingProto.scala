// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package shuffling.shuffling

object ShufflingProto extends _root_.scalapb.GeneratedFileObject {
  lazy val dependencies: Seq[_root_.scalapb.GeneratedFileObject] = Seq.empty
  lazy val messagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] =
    Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]](
      shuffling.shuffling.ShuffleRequest,
      shuffling.shuffling.ShuffleResponse
    )
  private lazy val ProtoBytes: _root_.scala.Array[Byte] =
      scalapb.Encoding.fromBase64(scala.collection.immutable.Seq(
  """Cg9zaHVmZmxpbmcucHJvdG8SCXNodWZmbGluZyIyCg5TaHVmZmxlUmVxdWVzdBIgCgVpbnB1dBgBIAEoCUIK4j8HEgVpbnB1d
  FIFaW5wdXQiNgoPU2h1ZmZsZVJlc3BvbnNlEiMKBm91dHB1dBgBIAEoCUIL4j8IEgZvdXRwdXRSBm91dHB1dDJPCglTaHVmZmxpb
  mcSQgoHU2h1ZmZsZRIZLnNodWZmbGluZy5TaHVmZmxlUmVxdWVzdBoaLnNodWZmbGluZy5TaHVmZmxlUmVzcG9uc2UiAGIGcHJvd
  G8z"""
      ).mkString)
  lazy val scalaDescriptor: _root_.scalapb.descriptors.FileDescriptor = {
    val scalaProto = com.google.protobuf.descriptor.FileDescriptorProto.parseFrom(ProtoBytes)
    _root_.scalapb.descriptors.FileDescriptor.buildFrom(scalaProto, dependencies.map(_.scalaDescriptor))
  }
  lazy val javaDescriptor: com.google.protobuf.Descriptors.FileDescriptor = {
    val javaProto = com.google.protobuf.DescriptorProtos.FileDescriptorProto.parseFrom(ProtoBytes)
    com.google.protobuf.Descriptors.FileDescriptor.buildFrom(javaProto, _root_.scala.Array(
    ))
  }
  @deprecated("Use javaDescriptor instead. In a future version this will refer to scalaDescriptor.", "ScalaPB 0.5.47")
  def descriptor: com.google.protobuf.Descriptors.FileDescriptor = javaDescriptor
}