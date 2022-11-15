#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_fcmb_sampletestingappwithcicd_MainActivity_getFacebookApiKey(JNIEnv *env, jobject instance) {
    return (*env)->  NewStringUTF(env, "1234567890");
}
JNIEXPORT jstring JNICALL
Java_com_fcmb_sampletestingappwithcicd_MainActivity_getBaseApi(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "ABCEFGHI123458765");
}

