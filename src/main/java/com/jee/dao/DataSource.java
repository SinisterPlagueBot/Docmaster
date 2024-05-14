package com.jee.dao;

import java.sql.Connection;

public interface DataSource{
	public  Connection getConnection();
}