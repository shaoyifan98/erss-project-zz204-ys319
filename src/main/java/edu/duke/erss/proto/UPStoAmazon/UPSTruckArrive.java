// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: withAmazon.proto

package edu.duke.erss.proto.UPStoAmazon;

/**
 * Protobuf type {@code tutorial.UPSTruckArrive}
 */
public final class UPSTruckArrive extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tutorial.UPSTruckArrive)
    UPSTruckArriveOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UPSTruckArrive.newBuilder() to construct.
  private UPSTruckArrive(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UPSTruckArrive() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UPSTruckArrive();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return edu.duke.erss.proto.UPStoAmazon.WithAmazon.internal_static_tutorial_UPSTruckArrive_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return edu.duke.erss.proto.UPStoAmazon.WithAmazon.internal_static_tutorial_UPSTruckArrive_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive.class, edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive.Builder.class);
  }

  private int bitField0_;
  public static final int SEQ_FIELD_NUMBER = 1;
  private long seq_;
  /**
   * <code>required int64 seq = 1;</code>
   * @return Whether the seq field is set.
   */
  @java.lang.Override
  public boolean hasSeq() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required int64 seq = 1;</code>
   * @return The seq.
   */
  @java.lang.Override
  public long getSeq() {
    return seq_;
  }

  public static final int TRUCKID_FIELD_NUMBER = 2;
  private int truckID_;
  /**
   * <code>required int32 truckID = 2;</code>
   * @return Whether the truckID field is set.
   */
  @java.lang.Override
  public boolean hasTruckID() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>required int32 truckID = 2;</code>
   * @return The truckID.
   */
  @java.lang.Override
  public int getTruckID() {
    return truckID_;
  }

  public static final int WHNUM_FIELD_NUMBER = 3;
  private int whnum_;
  /**
   * <code>required int32 whnum = 3;</code>
   * @return Whether the whnum field is set.
   */
  @java.lang.Override
  public boolean hasWhnum() {
    return ((bitField0_ & 0x00000004) != 0);
  }
  /**
   * <code>required int32 whnum = 3;</code>
   * @return The whnum.
   */
  @java.lang.Override
  public int getWhnum() {
    return whnum_;
  }

  public static final int SHIPID_FIELD_NUMBER = 4;
  private long shipid_;
  /**
   * <pre>
   *(repeated?)
   * </pre>
   *
   * <code>required int64 shipid = 4;</code>
   * @return Whether the shipid field is set.
   */
  @java.lang.Override
  public boolean hasShipid() {
    return ((bitField0_ & 0x00000008) != 0);
  }
  /**
   * <pre>
   *(repeated?)
   * </pre>
   *
   * <code>required int64 shipid = 4;</code>
   * @return The shipid.
   */
  @java.lang.Override
  public long getShipid() {
    return shipid_;
  }

  public static final int TRACKINGNUM_FIELD_NUMBER = 5;
  private long trackingNum_;
  /**
   * <code>optional int64 trackingNum = 5;</code>
   * @return Whether the trackingNum field is set.
   */
  @java.lang.Override
  public boolean hasTrackingNum() {
    return ((bitField0_ & 0x00000010) != 0);
  }
  /**
   * <code>optional int64 trackingNum = 5;</code>
   * @return The trackingNum.
   */
  @java.lang.Override
  public long getTrackingNum() {
    return trackingNum_;
  }

  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code tutorial.UPSTruckArrive}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tutorial.UPSTruckArrive)
      edu.duke.erss.proto.UPStoAmazon.UPSTruckArriveOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return edu.duke.erss.proto.UPStoAmazon.WithAmazon.internal_static_tutorial_UPSTruckArrive_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return edu.duke.erss.proto.UPStoAmazon.WithAmazon.internal_static_tutorial_UPSTruckArrive_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive.class, edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive.Builder.class);
    }

    // Construct using edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      seq_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      truckID_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      whnum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      shipid_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      trackingNum_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return edu.duke.erss.proto.UPStoAmazon.WithAmazon.internal_static_tutorial_UPSTruckArrive_descriptor;
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive getDefaultInstanceForType() {
      return edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive.getDefaultInstance();
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive build() {
      edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive buildPartial() {
      edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive result = new edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.seq_ = seq_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.truckID_ = truckID_;
        to_bitField0_ |= 0x00000002;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.whnum_ = whnum_;
        to_bitField0_ |= 0x00000004;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.shipid_ = shipid_;
        to_bitField0_ |= 0x00000008;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.trackingNum_ = trackingNum_;
        to_bitField0_ |= 0x00000010;
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    private int bitField0_;

    private long seq_ ;
    /**
     * <code>required int64 seq = 1;</code>
     * @return Whether the seq field is set.
     */
    @java.lang.Override
    public boolean hasSeq() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required int64 seq = 1;</code>
     * @return The seq.
     */
    @java.lang.Override
    public long getSeq() {
      return seq_;
    }
    /**
     * <code>required int64 seq = 1;</code>
     * @param value The seq to set.
     * @return This builder for chaining.
     */
    public Builder setSeq(long value) {
      bitField0_ |= 0x00000001;
      seq_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 seq = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSeq() {
      bitField0_ = (bitField0_ & ~0x00000001);
      seq_ = 0L;
      onChanged();
      return this;
    }

    private int truckID_ ;
    /**
     * <code>required int32 truckID = 2;</code>
     * @return Whether the truckID field is set.
     */
    @java.lang.Override
    public boolean hasTruckID() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required int32 truckID = 2;</code>
     * @return The truckID.
     */
    @java.lang.Override
    public int getTruckID() {
      return truckID_;
    }
    /**
     * <code>required int32 truckID = 2;</code>
     * @param value The truckID to set.
     * @return This builder for chaining.
     */
    public Builder setTruckID(int value) {
      bitField0_ |= 0x00000002;
      truckID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 truckID = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTruckID() {
      bitField0_ = (bitField0_ & ~0x00000002);
      truckID_ = 0;
      onChanged();
      return this;
    }

    private int whnum_ ;
    /**
     * <code>required int32 whnum = 3;</code>
     * @return Whether the whnum field is set.
     */
    @java.lang.Override
    public boolean hasWhnum() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>required int32 whnum = 3;</code>
     * @return The whnum.
     */
    @java.lang.Override
    public int getWhnum() {
      return whnum_;
    }
    /**
     * <code>required int32 whnum = 3;</code>
     * @param value The whnum to set.
     * @return This builder for chaining.
     */
    public Builder setWhnum(int value) {
      bitField0_ |= 0x00000004;
      whnum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 whnum = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearWhnum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      whnum_ = 0;
      onChanged();
      return this;
    }

    private long shipid_ ;
    /**
     * <pre>
     *(repeated?)
     * </pre>
     *
     * <code>required int64 shipid = 4;</code>
     * @return Whether the shipid field is set.
     */
    @java.lang.Override
    public boolean hasShipid() {
      return ((bitField0_ & 0x00000008) != 0);
    }
    /**
     * <pre>
     *(repeated?)
     * </pre>
     *
     * <code>required int64 shipid = 4;</code>
     * @return The shipid.
     */
    @java.lang.Override
    public long getShipid() {
      return shipid_;
    }
    /**
     * <pre>
     *(repeated?)
     * </pre>
     *
     * <code>required int64 shipid = 4;</code>
     * @param value The shipid to set.
     * @return This builder for chaining.
     */
    public Builder setShipid(long value) {
      bitField0_ |= 0x00000008;
      shipid_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *(repeated?)
     * </pre>
     *
     * <code>required int64 shipid = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearShipid() {
      bitField0_ = (bitField0_ & ~0x00000008);
      shipid_ = 0L;
      onChanged();
      return this;
    }

    private long trackingNum_ ;
    /**
     * <code>optional int64 trackingNum = 5;</code>
     * @return Whether the trackingNum field is set.
     */
    @java.lang.Override
    public boolean hasTrackingNum() {
      return ((bitField0_ & 0x00000010) != 0);
    }
    /**
     * <code>optional int64 trackingNum = 5;</code>
     * @return The trackingNum.
     */
    @java.lang.Override
    public long getTrackingNum() {
      return trackingNum_;
    }
    /**
     * <code>optional int64 trackingNum = 5;</code>
     * @param value The trackingNum to set.
     * @return This builder for chaining.
     */
    public Builder setTrackingNum(long value) {
      bitField0_ |= 0x00000010;
      trackingNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 trackingNum = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearTrackingNum() {
      bitField0_ = (bitField0_ & ~0x00000010);
      trackingNum_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tutorial.UPSTruckArrive)
  }

  // @@protoc_insertion_point(class_scope:tutorial.UPSTruckArrive)
  private static final edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive();
  }

  public static edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<UPSTruckArrive>
      PARSER = new com.google.protobuf.AbstractParser<UPSTruckArrive>() {
    @java.lang.Override
    public UPSTruckArrive parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(
                builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<UPSTruckArrive> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UPSTruckArrive> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public edu.duke.erss.proto.UPStoAmazon.UPSTruckArrive getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

