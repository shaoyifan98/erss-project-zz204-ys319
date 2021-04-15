// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: world_amazon.proto

package edu.duke.erss.ups.proto.AmazontoWorld;

public final class WorldAmazon {
  private WorldAmazon() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AProduct_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AProduct_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AInitWarehouse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AInitWarehouse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AConnect_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AConnect_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AConnected_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AConnected_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_APack_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_APack_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_APacked_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_APacked_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ALoaded_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ALoaded_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_APutOnTruck_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_APutOnTruck_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_APurchaseMore_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_APurchaseMore_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AErr_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AErr_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AQuery_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AQuery_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_APackage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_APackage_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ACommands_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ACommands_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AResponses_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_AResponses_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022world_amazon.proto\":\n\010AProduct\022\n\n\002id\030\001" +
      " \002(\003\022\023\n\013description\030\002 \002(\t\022\r\n\005count\030\003 \002(\005" +
      "\"2\n\016AInitWarehouse\022\n\n\002id\030\001 \002(\005\022\t\n\001x\030\002 \002(" +
      "\005\022\t\n\001y\030\003 \002(\005\"N\n\010AConnect\022\017\n\007worldid\030\001 \001(" +
      "\003\022\037\n\006initwh\030\002 \003(\0132\017.AInitWarehouse\022\020\n\010is" +
      "Amazon\030\003 \002(\010\"-\n\nAConnected\022\017\n\007worldid\030\001 " +
      "\002(\003\022\016\n\006result\030\002 \002(\t\"Q\n\005APack\022\r\n\005whnum\030\001 " +
      "\002(\005\022\031\n\006things\030\002 \003(\0132\t.AProduct\022\016\n\006shipid" +
      "\030\003 \002(\003\022\016\n\006seqnum\030\004 \002(\003\")\n\007APacked\022\016\n\006shi" +
      "pid\030\001 \002(\003\022\016\n\006seqnum\030\002 \002(\003\")\n\007ALoaded\022\016\n\006" +
      "shipid\030\001 \002(\003\022\016\n\006seqnum\030\002 \002(\003\"M\n\013APutOnTr" +
      "uck\022\r\n\005whnum\030\001 \002(\005\022\017\n\007truckid\030\002 \002(\005\022\016\n\006s" +
      "hipid\030\003 \002(\003\022\016\n\006seqnum\030\004 \002(\003\"I\n\rAPurchase" +
      "More\022\r\n\005whnum\030\001 \002(\005\022\031\n\006things\030\002 \003(\0132\t.AP" +
      "roduct\022\016\n\006seqnum\030\003 \002(\003\"9\n\004AErr\022\013\n\003err\030\001 " +
      "\002(\t\022\024\n\014originseqnum\030\002 \002(\003\022\016\n\006seqnum\030\003 \002(" +
      "\003\"+\n\006AQuery\022\021\n\tpackageid\030\001 \002(\003\022\016\n\006seqnum" +
      "\030\002 \002(\003\"=\n\010APackage\022\021\n\tpackageid\030\001 \002(\003\022\016\n" +
      "\006status\030\002 \002(\t\022\016\n\006seqnum\030\003 \002(\003\"\252\001\n\tAComma" +
      "nds\022\033\n\003buy\030\001 \003(\0132\016.APurchaseMore\022\026\n\006topa" +
      "ck\030\002 \003(\0132\006.APack\022\032\n\004load\030\003 \003(\0132\014.APutOnT" +
      "ruck\022\030\n\007queries\030\004 \003(\0132\007.AQuery\022\020\n\010simspe" +
      "ed\030\005 \001(\r\022\022\n\ndisconnect\030\006 \001(\010\022\014\n\004acks\030\007 \003" +
      "(\003\"\270\001\n\nAResponses\022\037\n\007arrived\030\001 \003(\0132\016.APu" +
      "rchaseMore\022\027\n\005ready\030\002 \003(\0132\010.APacked\022\030\n\006l" +
      "oaded\030\003 \003(\0132\010.ALoaded\022\020\n\010finished\030\004 \001(\010\022" +
      "\024\n\005error\030\005 \003(\0132\005.AErr\022\014\n\004acks\030\006 \003(\003\022 \n\rp" +
      "ackagestatus\030\007 \003(\0132\t.APackageB+\n%edu.duk" +
      "e.erss.ups.proto.AmazontoWorldH\002P\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_AProduct_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AProduct_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AProduct_descriptor,
        new java.lang.String[] { "Id", "Description", "Count", });
    internal_static_AInitWarehouse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_AInitWarehouse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AInitWarehouse_descriptor,
        new java.lang.String[] { "Id", "X", "Y", });
    internal_static_AConnect_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_AConnect_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AConnect_descriptor,
        new java.lang.String[] { "Worldid", "Initwh", "IsAmazon", });
    internal_static_AConnected_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_AConnected_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AConnected_descriptor,
        new java.lang.String[] { "Worldid", "Result", });
    internal_static_APack_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_APack_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_APack_descriptor,
        new java.lang.String[] { "Whnum", "Things", "Shipid", "Seqnum", });
    internal_static_APacked_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_APacked_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_APacked_descriptor,
        new java.lang.String[] { "Shipid", "Seqnum", });
    internal_static_ALoaded_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_ALoaded_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ALoaded_descriptor,
        new java.lang.String[] { "Shipid", "Seqnum", });
    internal_static_APutOnTruck_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_APutOnTruck_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_APutOnTruck_descriptor,
        new java.lang.String[] { "Whnum", "Truckid", "Shipid", "Seqnum", });
    internal_static_APurchaseMore_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_APurchaseMore_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_APurchaseMore_descriptor,
        new java.lang.String[] { "Whnum", "Things", "Seqnum", });
    internal_static_AErr_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_AErr_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AErr_descriptor,
        new java.lang.String[] { "Err", "Originseqnum", "Seqnum", });
    internal_static_AQuery_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_AQuery_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AQuery_descriptor,
        new java.lang.String[] { "Packageid", "Seqnum", });
    internal_static_APackage_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_APackage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_APackage_descriptor,
        new java.lang.String[] { "Packageid", "Status", "Seqnum", });
    internal_static_ACommands_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_ACommands_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ACommands_descriptor,
        new java.lang.String[] { "Buy", "Topack", "Load", "Queries", "Simspeed", "Disconnect", "Acks", });
    internal_static_AResponses_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_AResponses_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_AResponses_descriptor,
        new java.lang.String[] { "Arrived", "Ready", "Loaded", "Finished", "Error", "Acks", "Packagestatus", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}