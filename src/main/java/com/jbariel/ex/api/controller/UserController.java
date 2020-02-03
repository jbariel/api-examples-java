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
package com.jbariel.ex.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jbariel.ex.api.model.MyReturn;
import com.jbariel.ex.api.model.User;
import com.jbariel.ex.api.model.User.Gender;

import spark.Request;
import spark.Response;

public class UserController extends MyObjectController<User> {

	private List<User> userRepo = new ArrayList<>();

	public UserController() {
		super();

		userRepo.add(new User().withFirstName("Billy").withLastName("Bob").withGender(Gender.Male));
		userRepo.add(new User().withFirstName("John").withLastName("Travolta").withGender(Gender.Male));
		userRepo.add(new User().withFirstName("Joan").withLastName("Rivers").withGender(Gender.Female));
		userRepo.add(new User().withFirstName("Tom").withLastName("Cruise").withGender(Gender.Other));
		userRepo.add(new User().withFirstName("Foo").withLastName("Bar"));
	}

	public String getAll(Request req, Response res) {
		return serialize(new MyReturn<User>().withStatus(200).withMessage("Success").withRtnObjects(userRepo));
	}

	public String getByUuid(Request req, Response res) {
		MyReturn<User> rtn = new MyReturn<>();
		try {
			final UUID uuid = UUID.fromString(req.params("uuid"));
			User user = null;
			if (null == uuid) {
				rtn.withStatus(404).withMessage("Invalid UUID provided");
			} else {
				user = userRepo.stream().filter(u -> uuid.equals(u.getUuid())).findFirst().orElseGet(() -> null);
				if (null == user) {
					rtn.withStatus(404).withMessage("Cannot find user with UUID: '" + uuid + "'");
				} else {
					rtn.withStatus(200).withMessage("Success").withRtnObject(user);
				}
			}
		} catch (IllegalArgumentException e) {
			rtn.withStatus(404).withMessage("Invalid UUID provided").withMessage(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return serialize(rtn);
	}
}
