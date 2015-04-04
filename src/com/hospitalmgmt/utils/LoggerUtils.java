/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.utils;

import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 *
 * @author raunakshakya
 */
public class LoggerUtils {

    public static Logger logger = new Logger() {

        @Override
        public String getName() {
            return null;
        }

        @Override
        public boolean isTraceEnabled() {
            return true;
        }
        
        @Override
        public void trace(String string) {
        }

        @Override
        public void trace(String string, Object o) {
        }

        @Override
        public void trace(String string, Object o, Object o1) {
        }

        @Override
        public void trace(String string, Object[] os) {
        }

        @Override
        public void trace(String string, Throwable thrwbl) {
        }

        @Override
        public boolean isTraceEnabled(Marker marker) {
            return true;
        }

        @Override
        public void trace(Marker marker, String string) {
        }

        @Override
        public void trace(Marker marker, String string, Object o) {
        }

        @Override
        public void trace(Marker marker, String string, Object o, Object o1) {
        }

        @Override
        public void trace(Marker marker, String string, Object[] os) {
        }

        @Override
        public void trace(Marker marker, String string, Throwable thrwbl) {
        }

        @Override
        public boolean isDebugEnabled() {
            return true;
        }

        @Override
        public void debug(String string) {
        }

        @Override
        public void debug(String string, Object o) {
        }

        @Override
        public void debug(String string, Object o, Object o1) {
        }

        @Override
        public void debug(String string, Object[] os) {
        }

        @Override
        public void debug(String string, Throwable thrwbl) {
        }

        @Override
        public boolean isDebugEnabled(Marker marker) {
            return true;
        }

        @Override
        public void debug(Marker marker, String string) {
        }

        @Override
        public void debug(Marker marker, String string, Object o) {
        }

        @Override
        public void debug(Marker marker, String string, Object o, Object o1) {
        }

        @Override
        public void debug(Marker marker, String string, Object[] os) {
        }

        @Override
        public void debug(Marker marker, String string, Throwable thrwbl) {
        }

        @Override
        public boolean isInfoEnabled() {
            return true;
        }

        @Override
        public void info(String string) {
        }

        @Override
        public void info(String string, Object o) {
        }

        @Override
        public void info(String string, Object o, Object o1) {
        }

        @Override
        public void info(String string, Object[] os) {
        }

        @Override
        public void info(String string, Throwable thrwbl) {
        }

        @Override
        public boolean isInfoEnabled(Marker marker) {
            return true;
        }

        @Override
        public void info(Marker marker, String string) {
        }

        @Override
        public void info(Marker marker, String string, Object o) {
        }

        @Override
        public void info(Marker marker, String string, Object o, Object o1) {
        }

        @Override
        public void info(Marker marker, String string, Object[] os) {
        }

        @Override
        public void info(Marker marker, String string, Throwable thrwbl) {
        }

        @Override
        public boolean isWarnEnabled() {
            return true;
        }

        @Override
        public void warn(String string) {
        }

        @Override
        public void warn(String string, Object o) {
        }

        @Override
        public void warn(String string, Object[] os) {
        }

        @Override
        public void warn(String string, Object o, Object o1) {
        }

        @Override
        public void warn(String string, Throwable thrwbl) {
        }

        @Override
        public boolean isWarnEnabled(Marker marker) {
            return true;
        }

        @Override
        public void warn(Marker marker, String string) {
        }

        @Override
        public void warn(Marker marker, String string, Object o) {
        }

        @Override
        public void warn(Marker marker, String string, Object o, Object o1) {
        }

        @Override
        public void warn(Marker marker, String string, Object[] os) {
        }

        @Override
        public void warn(Marker marker, String string, Throwable thrwbl) {
        }

        @Override
        public boolean isErrorEnabled() {
            return true;
        }

        @Override
        public void error(String string) {
        }

        @Override
        public void error(String string, Object o) {
        }

        @Override
        public void error(String string, Object o, Object o1) {
        }

        @Override
        public void error(String string, Object[] os) {
        }

        @Override
        public void error(String string, Throwable thrwbl) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isErrorEnabled(Marker marker) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void error(Marker marker, String string) {
        }

        @Override
        public void error(Marker marker, String string, Object o) {
        }

        @Override
        public void error(Marker marker, String string, Object o, Object o1) {
        }

        @Override
        public void error(Marker marker, String string, Object[] os) {
        }

        @Override
        public void error(Marker marker, String string, Throwable thrwbl) {
        }
    };

}
