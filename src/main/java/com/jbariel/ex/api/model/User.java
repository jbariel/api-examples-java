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

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User extends MyObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Gender {
		Male, Female, Other,
	}

	public User() {
		super();
	}

	private String firstName;

	private String lastName;

	@JsonIgnore
	private Gender genderValue;

	private String gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public Gender getGenderValue() {
		if (null == this.genderValue && null != this.gender) {
			parseAndSetGenderCorrectly(this.gender);
		}
		return genderValue;
	}

	@JsonIgnore
	public void setGenderValue(Gender gender) {
		this.genderValue = gender;
		if (null != gender) {
			this.gender = gender.name();
		}
	}

	@JsonIgnore
	private void parseAndSetGenderCorrectly(String genderString) {
		this.gender = StringUtils.trimToNull(genderString);
		if (null != this.gender) {
			try {
				this.genderValue = Gender.valueOf(StringUtils.trimToEmpty(genderString));
			} catch (IllegalArgumentException e) {
				log.error("Could not parse gender of: '" + StringUtils.trimToEmpty(genderString) + "'");
				e.printStackTrace();
				this.genderValue = null;
				this.gender = null;
			}
		}

	}

	public String getGender() {
		if (null == this.gender && null != this.genderValue) {
			this.gender = getGenderValue().name();
		}
		return this.gender;
	}

	public void setGender(String genderString) {
		parseAndSetGenderCorrectly(genderString);
	}

	public User withFirstName(String firstName) {
		setFirstName(firstName);
		return this;
	}

	public User withLastName(String lastName) {
		setLastName(lastName);
		return this;
	}

	public User withGender(Gender gender) {
		setGenderValue(gender);
		return this;
	}

}
