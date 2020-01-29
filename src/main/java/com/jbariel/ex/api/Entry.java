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
package com.jbariel.ex.api;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;

import com.jbariel.ex.api.controller.ObjectReturn;
import com.jbariel.ex.api.controller.SimpleReturn;
import com.jbariel.ex.api.controller.UserController;

public class Entry {

	public static void main(String[] args) {
		port(8080);

		SimpleReturn simple = new SimpleReturn();

		ObjectReturn obj = new ObjectReturn();

		UserController users = new UserController();

		path("/api", () -> {
			path("/simple", () -> {
				get("/hello", simple::helloWorld);

				after("/*", (req, res) -> {
					res.header("Content-Type", "text/plain; charset=utf-8");
				});
			});
			path("/object", () -> {
				get("/hello", obj::helloWorld);

				get("/users", users::getAll);
				get("/users/:uuid", users::getByUuid);

				after("/*", (req, res) -> {
					res.header("Content-Type", "application/json; charset=utf-8");
				});

			});
		});

	}

}
