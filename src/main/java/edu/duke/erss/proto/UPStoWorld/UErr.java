// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: world_ups.proto

package edu.duke.erss.proto.UPStoWorld;

/**
 * Protobuf type {@code UErr}
 */
public final class UErr extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:UErr)
    UErrOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UErr.newBuilder() to construct.
  private UErr(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UErr() {
    err_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UErr();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UErr_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UErr_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            edu.duke.erss.proto.UPStoWorld.UErr.class, edu.duke.erss.proto.UPStoWorld.UErr.Builder.class);
  }

  private int bitField0_;
  public static final int ERR_FIELD_NUMBER = 1;
  private volatile java.lang.Object err_;
  /**
   * <code>required string err = 1;</code>
   * @return Whether the err field is set.
   */
  @java.lang.Override
  public boolean hasErr() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required string err = 1;</code>
   * @return The err.
   */
  @java.lang.Override
  public java.lang.String getErr() {
    java.lang.Object ref = err_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        err_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string err = 1;</code>
   * @return The bytes for err.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getErrBytes() {
    java.lang.Object ref = err_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      err_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ORIGINSEQNUM_FIELD_NUMBER = 2;
  private long originseqnum_;
  /**
   * <code>required int64 originseqnum = 2;</code>
   * @return Whether the originseqnum field is set.
   */
  @java.lang.Override
  public boolean hasOriginseqnum() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>required int64 originseqnum = 2;</code>
   * @return The originseqnum.
   */
  @java.lang.Override
  public long getOriginseqnum() {
    return originseqnum_;
  }

  public static final int SEQNUM_FIELD_NUMBER = 3;
  private long seqnum_;
  /**
   * <code>required int64 seqnum = 3;</code>
   * @return Whether the seqnum field is set.
   */
  @java.lang.Override
  public boolean hasSeqnum() {
    return ((bitField0_ & 0x00000004) != 0);
  }
  /**
   * <code>required int64 seqnum = 3;</code>
   * @return The seqnum.
   */
  @java.lang.Override
  public long getSeqnum() {
    return seqnum_;
  }

  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoWorld.UErr parseFrom(
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
  public static Builder newBuilder(edu.duke.erss.proto.UPStoWorld.UErr prototype) {
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
   * Protobuf type {@code UErr}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:UErr)
      edu.duke.erss.proto.UPStoWorld.UErrOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UErr_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UErr_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              edu.duke.erss.proto.UPStoWorld.UErr.class, edu.duke.erss.proto.UPStoWorld.UErr.Builder.class);
    }

    // Construct using edu.duke.erss.proto.UPStoWorld.UErr.newBuilder()
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
      err_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      originseqnum_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      seqnum_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UErr_descriptor;
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoWorld.UErr getDefaultInstanceForType() {
      return edu.duke.erss.proto.UPStoWorld.UErr.getDefaultInstance();
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoWorld.UErr build() {
      edu.duke.erss.proto.UPStoWorld.UErr result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoWorld.UErr buildPartial() {
      edu.duke.erss.proto.UPStoWorld.UErr result = new edu.duke.erss.proto.UPStoWorld.UErr(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.err_ = err_;
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.originseqnum_ = originseqnum_;
        to_bitField0_ |= 0x00000002;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.seqnum_ = seqnum_;
        to_bitField0_ |= 0x00000004;
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

    private java.lang.Object err_ = "";
    /**
     * <code>required string err = 1;</code>
     * @return Whether the err field is set.
     */
    public boolean hasErr() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required string err = 1;</code>
     * @return The err.
     */
    public java.lang.String getErr() {
      java.lang.Object ref = err_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          err_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string err = 1;</code>
     * @return The bytes for err.
     */
    public com.google.protobuf.ByteString
        getErrBytes() {
      java.lang.Object ref = err_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        err_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string err = 1;</code>
     * @param value The err to set.
     * @return This builder for chaining.
     */
    public Builder setErr(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      err_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string err = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearErr() {
      bitField0_ = (bitField0_ & ~0x00000001);
      err_ = getDefaultInstance().getErr();
      onChanged();
      return this;
    }
    /**
     * <code>required string err = 1;</code>
     * @param value The bytes for err to set.
     * @return This builder for chaining.
     */
    public Builder setErrBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      err_ = value;
      onChanged();
      return this;
    }

    private long originseqnum_ ;
    /**
     * <code>required int64 originseqnum = 2;</code>
     * @return Whether the originseqnum field is set.
     */
    @java.lang.Override
    public boolean hasOriginseqnum() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required int64 originseqnum = 2;</code>
     * @return The originseqnum.
     */
    @java.lang.Override
    public long getOriginseqnum() {
      return originseqnum_;
    }
    /**
     * <code>required int64 originseqnum = 2;</code>
     * @param value The originseqnum to set.
     * @return This builder for chaining.
     */
    public Builder setOriginseqnum(long value) {
      bitField0_ |= 0x00000002;
      originseqnum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 originseqnum = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearOriginseqnum() {
      bitField0_ = (bitField0_ & ~0x00000002);
      originseqnum_ = 0L;
      onChanged();
      return this;
    }

    private long seqnum_ ;
    /**
     * <code>required int64 seqnum = 3;</code>
     * @return Whether the seqnum field is set.
     */
    @java.lang.Override
    public boolean hasSeqnum() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>required int64 seqnum = 3;</code>
     * @return The seqnum.
     */
    @java.lang.Override
    public long getSeqnum() {
      return seqnum_;
    }
    /**
     * <code>required int64 seqnum = 3;</code>
     * @param value The seqnum to set.
     * @return This builder for chaining.
     */
    public Builder setSeqnum(long value) {
      bitField0_ |= 0x00000004;
      seqnum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 seqnum = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearSeqnum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      seqnum_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:UErr)
  }

  // @@protoc_insertion_point(class_scope:UErr)
  private static final edu.duke.erss.proto.UPStoWorld.UErr DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new edu.duke.erss.proto.UPStoWorld.UErr();
  }

  public static edu.duke.erss.proto.UPStoWorld.UErr getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<UErr>
      PARSER = new com.google.protobuf.AbstractParser<UErr>() {
    @java.lang.Override
    public UErr parsePartialFrom(
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

  public static com.google.protobuf.Parser<UErr> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UErr> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public edu.duke.erss.proto.UPStoWorld.UErr getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

