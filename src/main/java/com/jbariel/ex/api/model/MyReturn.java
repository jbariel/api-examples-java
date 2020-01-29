/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.jbariel.ex.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple return object
 * 
 * @author jarrettbariel
 *
 */
public class MyReturn<O extends Object> {

	public MyReturn() {
		super();
	}

	private int status;

	private List<String> messages = new ArrayList<>();

	private O rtnObject;

	private List<O> rtnObjects = new ArrayList<>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		if (null == messages) {
			this.messages = new ArrayList<>();
		} else {
			this.messages.addAll(messages);
		}
	}

	public void addMessage(String message) {
		this.messages.add(message);
	}

	public O getRtnObject() {
		return (null == rtnObject && !rtnObjects.isEmpty()) ? rtnObjects.get(0) : rtnObject;
	}

	public void setRtnObject(O obj) {
		this.rtnObject = obj;
	}

	public List<O> getRtnObjects() {
		return rtnObjects;
	}

	public void setRtnObjects(List<O> objs) {
		if (null == objs) {
			this.rtnObjects = new ArrayList<>();
		} else {
			this.rtnObjects.addAll(objs);
		}
	}

	public void addRtnObj(O obj) {
		this.rtnObjects.add(obj);
	}

	public MyReturn<O> withStatus(int status) {
		setStatus(status);
		return this;
	}

	public MyReturn<O> withMessage(String message) {
		addMessage(message);
		return this;
	}

	public MyReturn<O> withMessages(List<String> messages) {
		setMessages(messages);
		return this;
	}

	public MyReturn<O> withRtnObject(O obj) {
		setRtnObject(obj);
		return this;
	}

	public MyReturn<O> withRtnObjects(List<O> objs) {
		setRtnObjects(objs);
		return this;
	}

	@Override
	public String toString() {
		return "[" + getStatus() + "] => \n" + String.join("\n", getMessages()) + "\n\n Object: " + getRtnObject();
	}
}
