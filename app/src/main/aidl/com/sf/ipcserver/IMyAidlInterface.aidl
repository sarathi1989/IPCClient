// IMyAidlInterface.aidl
package com.sf.ipcserver;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            String  getMessage(String name);
            int getAddition(int val1,int val2);

         //   int getRandomNumber();
}