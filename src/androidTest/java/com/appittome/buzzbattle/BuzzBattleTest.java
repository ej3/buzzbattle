package com.appittome.buzzbattle;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.appittome.buzzbattle.BuzzBattleTest \
 * com.appittome.buzzbattle.tests/android.test.InstrumentationTestRunner
 */
public class BuzzBattleTest extends ActivityInstrumentationTestCase2<BuzzBattle> {

    public BuzzBattleTest() {
        super("com.appittome.buzzbattle", BuzzBattle.class);
    }

}
