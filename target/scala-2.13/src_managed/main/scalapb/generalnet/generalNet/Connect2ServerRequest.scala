// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package generalnet.generalNet

@SerialVersionUID(0L)
final case class Connect2ServerRequest(
    workerIpAddress: _root_.scala.Predef.String = "",
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[Connect2ServerRequest] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = workerIpAddress
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(1, __value)
        }
      };
      __size += unknownFields.serializedSize
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var __size = __serializedSizeMemoized
      if (__size == 0) {
        __size = __computeSerializedSize() + 1
        __serializedSizeMemoized = __size
      }
      __size - 1
      
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = workerIpAddress
        if (!__v.isEmpty) {
          _output__.writeString(1, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withWorkerIpAddress(__v: _root_.scala.Predef.String): Connect2ServerRequest = copy(workerIpAddress = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = workerIpAddress
          if (__t != "") __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PString(workerIpAddress)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: generalnet.generalNet.Connect2ServerRequest.type = generalnet.generalNet.Connect2ServerRequest
    // @@protoc_insertion_point(GeneratedMessage[generalnet.Connect2ServerRequest])
}

object Connect2ServerRequest extends scalapb.GeneratedMessageCompanion[generalnet.generalNet.Connect2ServerRequest] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[generalnet.generalNet.Connect2ServerRequest] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): generalnet.generalNet.Connect2ServerRequest = {
    var __workerIpAddress: _root_.scala.Predef.String = ""
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __workerIpAddress = _input__.readStringRequireUtf8()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    generalnet.generalNet.Connect2ServerRequest(
        workerIpAddress = __workerIpAddress,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[generalnet.generalNet.Connect2ServerRequest] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      generalnet.generalNet.Connect2ServerRequest(
        workerIpAddress = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = GeneralNetProto.javaDescriptor.getMessageTypes().get(0)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = GeneralNetProto.scalaDescriptor.messages(0)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = generalnet.generalNet.Connect2ServerRequest(
    workerIpAddress = ""
  )
  implicit class Connect2ServerRequestLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, generalnet.generalNet.Connect2ServerRequest]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, generalnet.generalNet.Connect2ServerRequest](_l) {
    def workerIpAddress: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.workerIpAddress)((c_, f_) => c_.copy(workerIpAddress = f_))
  }
  final val WORKERIPADDRESS_FIELD_NUMBER = 1
  def of(
    workerIpAddress: _root_.scala.Predef.String
  ): _root_.generalnet.generalNet.Connect2ServerRequest = _root_.generalnet.generalNet.Connect2ServerRequest(
    workerIpAddress
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[generalnet.Connect2ServerRequest])
}
