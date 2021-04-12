// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: world_ups.proto

package edu.duke.erss.proto.UPStoWorld;

public interface UResponsesOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UResponses)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .UFinished completions = 1;</code>
   */
  java.util.List<edu.duke.erss.proto.UPStoWorld.UFinished> 
      getCompletionsList();
  /**
   * <code>repeated .UFinished completions = 1;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UFinished getCompletions(int index);
  /**
   * <code>repeated .UFinished completions = 1;</code>
   */
  int getCompletionsCount();
  /**
   * <code>repeated .UFinished completions = 1;</code>
   */
  java.util.List<? extends edu.duke.erss.proto.UPStoWorld.UFinishedOrBuilder> 
      getCompletionsOrBuilderList();
  /**
   * <code>repeated .UFinished completions = 1;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UFinishedOrBuilder getCompletionsOrBuilder(
      int index);

  /**
   * <code>repeated .UDeliveryMade delivered = 2;</code>
   */
  java.util.List<edu.duke.erss.proto.UPStoWorld.UDeliveryMade> 
      getDeliveredList();
  /**
   * <code>repeated .UDeliveryMade delivered = 2;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UDeliveryMade getDelivered(int index);
  /**
   * <code>repeated .UDeliveryMade delivered = 2;</code>
   */
  int getDeliveredCount();
  /**
   * <code>repeated .UDeliveryMade delivered = 2;</code>
   */
  java.util.List<? extends edu.duke.erss.proto.UPStoWorld.UDeliveryMadeOrBuilder> 
      getDeliveredOrBuilderList();
  /**
   * <code>repeated .UDeliveryMade delivered = 2;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UDeliveryMadeOrBuilder getDeliveredOrBuilder(
      int index);

  /**
   * <code>optional bool finished = 3;</code>
   * @return Whether the finished field is set.
   */
  boolean hasFinished();
  /**
   * <code>optional bool finished = 3;</code>
   * @return The finished.
   */
  boolean getFinished();

  /**
   * <code>repeated int64 acks = 4;</code>
   * @return A list containing the acks.
   */
  java.util.List<java.lang.Long> getAcksList();
  /**
   * <code>repeated int64 acks = 4;</code>
   * @return The count of acks.
   */
  int getAcksCount();
  /**
   * <code>repeated int64 acks = 4;</code>
   * @param index The index of the element to return.
   * @return The acks at the given index.
   */
  long getAcks(int index);

  /**
   * <code>repeated .UTruck truckstatus = 5;</code>
   */
  java.util.List<edu.duke.erss.proto.UPStoWorld.UTruck> 
      getTruckstatusList();
  /**
   * <code>repeated .UTruck truckstatus = 5;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UTruck getTruckstatus(int index);
  /**
   * <code>repeated .UTruck truckstatus = 5;</code>
   */
  int getTruckstatusCount();
  /**
   * <code>repeated .UTruck truckstatus = 5;</code>
   */
  java.util.List<? extends edu.duke.erss.proto.UPStoWorld.UTruckOrBuilder> 
      getTruckstatusOrBuilderList();
  /**
   * <code>repeated .UTruck truckstatus = 5;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UTruckOrBuilder getTruckstatusOrBuilder(
      int index);

  /**
   * <code>repeated .UErr error = 6;</code>
   */
  java.util.List<edu.duke.erss.proto.UPStoWorld.UErr> 
      getErrorList();
  /**
   * <code>repeated .UErr error = 6;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UErr getError(int index);
  /**
   * <code>repeated .UErr error = 6;</code>
   */
  int getErrorCount();
  /**
   * <code>repeated .UErr error = 6;</code>
   */
  java.util.List<? extends edu.duke.erss.proto.UPStoWorld.UErrOrBuilder> 
      getErrorOrBuilderList();
  /**
   * <code>repeated .UErr error = 6;</code>
   */
  edu.duke.erss.proto.UPStoWorld.UErrOrBuilder getErrorOrBuilder(
      int index);
}
