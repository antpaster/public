LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE    := SenderSocket
LOCAL_SRC_FILES := SenderSocket.cpp sender_wrap.cxx
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog
#LOCAL_LDLIBS += -llog
#LOCAL_SHARED_LIBRARIES += liblog
#LOCAL_LDFLAGS := -llog
include $(BUILD_SHARED_LIBRARY)

