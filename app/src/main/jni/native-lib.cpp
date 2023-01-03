//
// Created by SUSA on 2023/1/3.
//
//
// Created by marlon on 2020/9/13.
//
#include <jni.h>

extern "C"
JNIEXPORT jstring
Java_com_wg_tcpclient_HelloJNI_helloJNI(JNIEnv *env, jclass clazz) {
    // TODO: implement helloJNI()
    return env->NewStringUTF("Hello From JNI!");
}




