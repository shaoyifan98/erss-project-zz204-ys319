// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: world_amazon.proto

package edu.duke.erss.ups.proto.AmazontoWorld;

public interface AConnectOrBuilder extends
    // @@protoc_insertion_point(interface_extends:AConnect)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional int64 worldid = 1;</code>
   * @return Whether the worldid field is set.
   */
  boolean hasWorldid();
  /**
   * <code>optional int64 worldid = 1;</code>
   * @return The worldid.
   */
  long getWorldid();

  /**
   * <code>repeated .AInitWarehouse initwh = 2;</code>
   */
  java.util.List<edu.duke.erss.ups.proto.AmazontoWorld.AInitWarehouse> 
      getInitwhList();
  /**
   * <code>repeated .AInitWarehouse initwh = 2;</code>
   */
  edu.duke.erss.ups.proto.AmazontoWorld.AInitWarehouse getInitwh(int index);
  /**
   * <code>repeated .AInitWarehouse initwh = 2;</code>
   */
  int getInitwhCount();
  /**
   * <code>repeated .AInitWarehouse initwh = 2;</code>
   */
  java.util.List<? extends edu.duke.erss.ups.proto.AmazontoWorld.AInitWarehouseOrBuilder> 
      getInitwhOrBuilderList();
  /**
   * <code>repeated .AInitWarehouse initwh = 2;</code>
   */
  edu.duke.erss.ups.proto.AmazontoWorld.AInitWarehouseOrBuilder getInitwhOrBuilder(
      int index);

  /**
   * <code>required bool isAmazon = 3;</code>
   * @return Whether the isAmazon field is set.
   */
  boolean hasIsAmazon();
  /**
   * <code>required bool isAmazon = 3;</code>
   * @return The isAmazon.
   */
  boolean getIsAmazon();
}
