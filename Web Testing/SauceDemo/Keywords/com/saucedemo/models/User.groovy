package com.saucedemo.models

/**
 * Accounts published on the Swag Labs login page. All of them share the same password,
 * which is read from the execution profile (GlobalVariable.Password).
 */
enum User {

	STANDARD('standard_user'),
	LOCKED_OUT('locked_out_user'),
	PROBLEM('problem_user'),
	PERFORMANCE_GLITCH('performance_glitch_user'),
	ERROR('error_user'),
	VISUAL('visual_user')

	final String username

	User(String username) {
		this.username = username
	}
}
