// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: world_amazon.proto

package edu.duke.erss.ups.proto.AmazontoWorld;

/**
 * Protobuf type {@code APacked}
 */
public final class APacked extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:APacked)
    APackedOrBuilder {
private static final long serialVersionUID = 0L;
  // Use APacked.newBuilder() to construct.
  private APacked(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private APacked() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new APacked();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return edu.duke.erss.ups.proto.AmazontoWorld.WorldAmazon.internal_static_APacked_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return edu.duke.erss.ups.proto.AmazontoWorld.WorldAmazon.internal_static_APacked_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            edu.duke.erss.ups.proto.AmazontoWorld.APacked.class, edu.duke.erss.ups.proto.AmazontoWorld.APacked.Builder.class);
  }

  private int bitField0_;
  public static final int SHIPID_FIELD_NUMBER = 1;
  private long shipid_;
  /**
   * <code>required int64 shipid = 1;</code>
   * @return Whether the shipid field is set.
   */
  @java.lang.Override
  public boolean hasShipid() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>required int64 shipid = 1;</code>
   * @return The shipid.
   */
  @java.lang.Override
  public long getShipid() {
    return shipid_;
  }

  public static final int SEQNUM_FIELD_NUMBER = 2;
  private long seqnum_;
  /**
   * <code>required int64 seqnum = 2;</code>
   * @return Whether the seqnum field is set.
   */
  @java.lang.Override
  public boolean hasSeqnum() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>required int64 seqnum = 2;</code>
   * @return The seqnum.
   */
  @java.lang.Override
  public long getSeqnum() {
    return seqnum_;
  }

  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked parseFrom(
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
  public static Builder newBuilder(edu.duke.erss.ups.proto.AmazontoWorld.APacked prototype) {
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
   * Protobuf type {@code APacked}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:APacked)
      edu.duke.erss.ups.proto.AmazontoWorld.APackedOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return edu.duke.erss.ups.proto.AmazontoWorld.WorldAmazon.internal_static_APacked_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return edu.duke.erss.ups.proto.AmazontoWorld.WorldAmazon.internal_static_APacked_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              edu.duke.erss.ups.proto.AmazontoWorld.APacked.class, edu.duke.erss.ups.proto.AmazontoWorld.APacked.Builder.class);
    }

    // Construct using edu.duke.erss.ups.proto.AmazontoWorld.APacked.newBuilder()
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
      shipid_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      seqnum_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return edu.duke.erss.ups.proto.AmazontoWorld.WorldAmazon.internal_static_APacked_descriptor;
    }

    @java.lang.Override
    public edu.duke.erss.ups.proto.AmazontoWorld.APacked getDefaultInstanceForType() {
      return edu.duke.erss.ups.proto.AmazontoWorld.APacked.getDefaultInstance();
    }

    @java.lang.Override
    public edu.duke.erss.ups.proto.AmazontoWorld.APacked build() {
      edu.duke.erss.ups.proto.AmazontoWorld.APacked result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public edu.duke.erss.ups.proto.AmazontoWorld.APacked buildPartial() {
      edu.duke.erss.ups.proto.AmazontoWorld.APacked result = new edu.duke.erss.ups.proto.AmazontoWorld.APacked(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.shipid_ = shipid_;
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.seqnum_ = seqnum_;
        to_bitField0_ |= 0x00000002;
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

    private long shipid_ ;
    /**
     * <code>required int64 shipid = 1;</code>
     * @return Whether the shipid field is set.
     */
    @java.lang.Override
    public boolean hasShipid() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required int64 shipid = 1;</code>
     * @return The shipid.
     */
    @java.lang.Override
    public long getShipid() {
      return shipid_;
    }
    /**
     * <code>required int64 shipid = 1;</code>
     * @param value The shipid to set.
     * @return This builder for chaining.
     */
    public Builder setShipid(long value) {
      bitField0_ |= 0x00000001;
      shipid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 shipid = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearShipid() {
      bitField0_ = (bitField0_ & ~0x00000001);
      shipid_ = 0L;
      onChanged();
      return this;
    }

    private long seqnum_ ;
    /**
     * <code>required int64 seqnum = 2;</code>
     * @return Whether the seqnum field is set.
     */
    @java.lang.Override
    public boolean hasSeqnum() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required int64 seqnum = 2;</code>
     * @return The seqnum.
     */
    @java.lang.Override
    public long getSeqnum() {
      return seqnum_;
    }
    /**
     * <code>required int64 seqnum = 2;</code>
     * @param value The seqnum to set.
     * @return This builder for chaining.
     */
    public Builder setSeqnum(long value) {
      bitField0_ |= 0x00000002;
      seqnum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 seqnum = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearSeqnum() {
      bitField0_ = (bitField0_ & ~0x00000002);
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


    // @@protoc_insertion_point(builder_scope:APacked)
  }

  // @@protoc_insertion_point(class_scope:APacked)
  private static final edu.duke.erss.ups.proto.AmazontoWorld.APacked DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new edu.duke.erss.ups.proto.AmazontoWorld.APacked();
  }

  public static edu.duke.erss.ups.proto.AmazontoWorld.APacked getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<APacked>
      PARSER = new com.google.protobuf.AbstractParser<APacked>() {
    @java.lang.Override
    public APacked parsePartialFrom(
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

  public static com.google.protobuf.Parser<APacked> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<APacked> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public edu.duke.erss.ups.proto.AmazontoWorld.APacked getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

