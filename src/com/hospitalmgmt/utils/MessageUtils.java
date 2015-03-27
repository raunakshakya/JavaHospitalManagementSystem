/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author raunakshakya
 */
public class MessageUtils {

    private static final String MESSAGES_LOCATION = "com.hospitalmgmt.i18n.MessageBundle";
    public static ResourceBundle MESSAGES = ResourceBundle.getBundle(MessageUtils.MESSAGES_LOCATION, Locale.US);

}
