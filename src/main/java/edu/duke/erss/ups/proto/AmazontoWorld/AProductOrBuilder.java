// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: world_amazon.proto

package edu.duke.erss.ups.proto.AmazontoWorld;

public interface AProductOrBuilder extends
    // @@protoc_insertion_point(interface_extends:AProduct)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int64 id = 1;</code>
   * @return Whether the id field is set.
   */
  boolean hasId();
  /**
   * <code>required int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>required string description = 2;</code>
   * @return Whether the description field is set.
   */
  boolean hasDescription();
  /**
   * <code>required string description = 2;</code>
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   * <code>required string description = 2;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>required int32 count = 3;</code>
   * @return Whether the count field is set.
   */
  boolean hasCount();
  /**
   * <code>required int32 count = 3;</code>
   * @return The count.
   */
  int getCount();
}