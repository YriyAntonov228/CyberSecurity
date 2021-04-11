package com.antonov.reporting;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.DocFlavor;

import com.antonov.PeopleInfo;

public class AcessReportingWriter {

	private Connection connection;
	private Statement statement;

	private String tableName = "Вошедшие";

	public void connect(File file) throws SQLException {
		connection = DriverManager.getConnection("jdbc:ucanaccess://" + file.getAbsolutePath());
		statement = connection.createStatement();
	}

	public void addPeopleInfo(PeopleInfo info) throws SQLException {

		String sqlRequest = "INSERT INTO " + tableName + "(Фото, Фамилия, Имя, Отчество, Дата, Вход, Гость) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = connection.prepareStatement(sqlRequest);

		pst.setString(1, "#" + info.getPathToImage());
		pst.setString(2, info.getSurname());
		pst.setString(3, info.getName());
		pst.setString(4, info.getPatronymic());
		pst.setDate(5, toSqlDate(info.getEntryDate()));
		pst.setBoolean(6, info.isEntry());
		pst.setBoolean(7, info.isGuest());

		pst.executeUpdate();
	}

	public static java.sql.Date toSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
}
