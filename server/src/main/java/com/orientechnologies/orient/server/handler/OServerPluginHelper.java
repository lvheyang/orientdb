/*
 * Copyright 2010-2012 Luca Garulli (l.garulli--at--orientechnologies.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orientechnologies.orient.server.handler;

import java.util.Collection;

import com.orientechnologies.orient.server.OClientConnection;
import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.plugin.OServerPluginInfo;

public class OServerPluginHelper {

  public static void invokeHandlerCallbackOnClientConnection(final OServer iServer, final OClientConnection connection) {
    final Collection<OServerPluginInfo> handlers = iServer.getPlugins();
    if (handlers != null)
      for (OServerPluginInfo handler : handlers) {
        handler.getInstance().onClientConnection(connection);
      }
  }

  public static void invokeHandlerCallbackOnClientDisconnection(final OServer iServer, final OClientConnection connection) {
    final Collection<OServerPluginInfo> handlers = iServer.getPlugins();
    if (handlers != null)
      for (OServerPluginInfo handler : handlers) {
        handler.getInstance().onClientDisconnection(connection);
      }
  }

  public static void invokeHandlerCallbackOnBeforeClientRequest(final OServer iServer, final OClientConnection connection,
      final byte iRequestType) {
    final Collection<OServerPluginInfo> handlers = iServer.getPlugins();
    if (handlers != null)
      for (OServerPluginInfo handler : handlers) {
        handler.getInstance().onBeforeClientRequest(connection, iRequestType);
      }
  }

  public static void invokeHandlerCallbackOnAfterClientRequest(final OServer iServer, final OClientConnection connection,
      final byte iRequestType) {
    final Collection<OServerPluginInfo> handlers = iServer.getPlugins();
    if (handlers != null)
      for (OServerPluginInfo handler : handlers) {
        handler.getInstance().onAfterClientRequest(connection, iRequestType);
      }
  }

  public static void invokeHandlerCallbackOnClientError(final OServer iServer, final OClientConnection connection,
      final Throwable iThrowable) {
    final Collection<OServerPluginInfo> handlers = iServer.getPlugins();
    if (handlers != null)
      for (OServerPluginInfo handler : handlers) {
        handler.getInstance().onClientError(connection, iThrowable);
      }
  }

}