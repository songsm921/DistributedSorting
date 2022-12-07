// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package generalnet.generalNet

@SerialVersionUID(0L)
final case class StartShufflingMsg2MasterResponse(
    nextServerWorkerID: _root_.scala.Int = 0,
    unknownFields: _root_.scalapb.UnknownFieldSet = _root_.scalapb.UnknownFieldSet.empty
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[StartShufflingMsg2MasterResponse] {
    @transient
    private[this] var __serializedSizeMemoized: _root_.scala.Int = 0
    private[this] def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = nextServerWorkerID
        if (__value != 0) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeInt32Size(1, __value)
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
        val __v = nextServerWorkerID
        if (__v != 0) {
          _output__.writeInt32(1, __v)
        }
      };
      unknownFields.writeTo(_output__)
    }
    def withNextServerWorkerID(__v: _root_.scala.Int): StartShufflingMsg2MasterResponse = copy(nextServerWorkerID = __v)
    def withUnknownFields(__v: _root_.scalapb.UnknownFieldSet) = copy(unknownFields = __v)
    def discardUnknownFields = copy(unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = nextServerWorkerID
          if (__t != 0) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PInt(nextServerWorkerID)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToUnicodeString(this)
    def companion: generalnet.generalNet.StartShufflingMsg2MasterResponse.type = generalnet.generalNet.StartShufflingMsg2MasterResponse
    // @@protoc_insertion_point(GeneratedMessage[generalnet.StartShufflingMsg2MasterResponse])
}

object StartShufflingMsg2MasterResponse extends scalapb.GeneratedMessageCompanion[generalnet.generalNet.StartShufflingMsg2MasterResponse] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[generalnet.generalNet.StartShufflingMsg2MasterResponse] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): generalnet.generalNet.StartShufflingMsg2MasterResponse = {
    var __nextServerWorkerID: _root_.scala.Int = 0
    var `_unknownFields__`: _root_.scalapb.UnknownFieldSet.Builder = null
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 8 =>
          __nextServerWorkerID = _input__.readInt32()
        case tag =>
          if (_unknownFields__ == null) {
            _unknownFields__ = new _root_.scalapb.UnknownFieldSet.Builder()
          }
          _unknownFields__.parseField(tag, _input__)
      }
    }
    generalnet.generalNet.StartShufflingMsg2MasterResponse(
        nextServerWorkerID = __nextServerWorkerID,
        unknownFields = if (_unknownFields__ == null) _root_.scalapb.UnknownFieldSet.empty else _unknownFields__.result()
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[generalnet.generalNet.StartShufflingMsg2MasterResponse] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      generalnet.generalNet.StartShufflingMsg2MasterResponse(
        nextServerWorkerID = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Int]).getOrElse(0)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = GeneralNetProto.javaDescriptor.getMessageTypes().get(9)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = GeneralNetProto.scalaDescriptor.messages(9)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = generalnet.generalNet.StartShufflingMsg2MasterResponse(
    nextServerWorkerID = 0
  )
  implicit class StartShufflingMsg2MasterResponseLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, generalnet.generalNet.StartShufflingMsg2MasterResponse]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, generalnet.generalNet.StartShufflingMsg2MasterResponse](_l) {
    def nextServerWorkerID: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.nextServerWorkerID)((c_, f_) => c_.copy(nextServerWorkerID = f_))
  }
  final val NEXTSERVERWORKERID_FIELD_NUMBER = 1
  def of(
    nextServerWorkerID: _root_.scala.Int
  ): _root_.generalnet.generalNet.StartShufflingMsg2MasterResponse = _root_.generalnet.generalNet.StartShufflingMsg2MasterResponse(
    nextServerWorkerID
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[generalnet.StartShufflingMsg2MasterResponse])
}
