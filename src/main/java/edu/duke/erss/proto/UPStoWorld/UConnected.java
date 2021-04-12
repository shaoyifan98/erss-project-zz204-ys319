// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: world_ups.proto

package edu.duke.erss.proto.UPStoWorld;

/**
 * Protobuf type {@code UConnected}
 */
public final class UConnected extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:UConnected)
    UConnectedOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UConnected.newBuilder() to construct.
  private UConnected(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UConnected() {
    result_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UConnected();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UConnected_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UConnected_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            edu.duke.erss.proto.UPStoWorld.UConnected.class, edu.duke.erss.proto.UPStoWorld.UConnected.Builder.class);
  }

  private int bitField0_;
  public static final int WORLDID_FIELD_NUMBER = 1;
  private long worldid_;
  /**
   * <code>required int64 worldid = 1;</code>
   * @return Whether the worldid field is set.
   */
  @java.lang.Override
  public boolean hasWorldid() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required int64 worldid = 1;</code>
   * @return The worldid.
   */
  @java.lang.Override
  public long getWorldid() {
    return worldid_;
  }

  public static final int RESULT_FIELD_NUMBER = 2;
  private volatile java.lang.Object result_;
  /**
   * <code>required string result = 2;</code>
   * @return Whether the result field is set.
   */
  @java.lang.Override
  public boolean hasResult() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>required string result = 2;</code>
   * @return The result.
   */
  @java.lang.Override
  public java.lang.String getResult() {
    java.lang.Object ref = result_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        result_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string result = 2;</code>
   * @return The bytes for result.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getResultBytes() {
    java.lang.Object ref = result_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      result_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.proto.UPStoWorld.UConnected parseFrom(
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
  public static Builder newBuilder(edu.duke.erss.proto.UPStoWorld.UConnected prototype) {
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
   * Protobuf type {@code UConnected}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:UConnected)
      edu.duke.erss.proto.UPStoWorld.UConnectedOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UConnected_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UConnected_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              edu.duke.erss.proto.UPStoWorld.UConnected.class, edu.duke.erss.proto.UPStoWorld.UConnected.Builder.class);
    }

    // Construct using edu.duke.erss.proto.UPStoWorld.UConnected.newBuilder()
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
      worldid_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      result_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return edu.duke.erss.proto.UPStoWorld.WorldUps.internal_static_UConnected_descriptor;
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoWorld.UConnected getDefaultInstanceForType() {
      return edu.duke.erss.proto.UPStoWorld.UConnected.getDefaultInstance();
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoWorld.UConnected build() {
      edu.duke.erss.proto.UPStoWorld.UConnected result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public edu.duke.erss.proto.UPStoWorld.UConnected buildPartial() {
      edu.duke.erss.proto.UPStoWorld.UConnected result = new edu.duke.erss.proto.UPStoWorld.UConnected(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.worldid_ = worldid_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        to_bitField0_ |= 0x00000002;
      }
      result.result_ = result_;
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

    private long worldid_ ;
    /**
     * <code>required int64 worldid = 1;</code>
     * @return Whether the worldid field is set.
     */
    @java.lang.Override
    public boolean hasWorldid() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required int64 worldid = 1;</code>
     * @return The worldid.
     */
    @java.lang.Override
    public long getWorldid() {
      return worldid_;
    }
    /**
     * <code>required int64 worldid = 1;</code>
     * @param value The worldid to set.
     * @return This builder for chaining.
     */
    public Builder setWorldid(long value) {
      bitField0_ |= 0x00000001;
      worldid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 worldid = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearWorldid() {
      bitField0_ = (bitField0_ & ~0x00000001);
      worldid_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object result_ = "";
    /**
     * <code>required string result = 2;</code>
     * @return Whether the result field is set.
     */
    public boolean hasResult() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required string result = 2;</code>
     * @return The result.
     */
    public java.lang.String getResult() {
      java.lang.Object ref = result_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          result_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string result = 2;</code>
     * @return The bytes for result.
     */
    public com.google.protobuf.ByteString
        getResultBytes() {
      java.lang.Object ref = result_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        result_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string result = 2;</code>
     * @param value The result to set.
     * @return This builder for chaining.
     */
    public Builder setResult(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      result_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string result = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearResult() {
      bitField0_ = (bitField0_ & ~0x00000002);
      result_ = getDefaultInstance().getResult();
      onChanged();
      return this;
    }
    /**
     * <code>required string result = 2;</code>
     * @param value The bytes for result to set.
     * @return This builder for chaining.
     */
    public Builder setResultBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      result_ = value;
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


    // @@protoc_insertion_point(builder_scope:UConnected)
  }

  // @@protoc_insertion_point(class_scope:UConnected)
  private static final edu.duke.erss.proto.UPStoWorld.UConnected DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new edu.duke.erss.proto.UPStoWorld.UConnected();
  }

  public static edu.duke.erss.proto.UPStoWorld.UConnected getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<UConnected>
      PARSER = new com.google.protobuf.AbstractParser<UConnected>() {
    @java.lang.Override
    public UConnected parsePartialFrom(
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

  public static com.google.protobuf.Parser<UConnected> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UConnected> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public edu.duke.erss.proto.UPStoWorld.UConnected getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

