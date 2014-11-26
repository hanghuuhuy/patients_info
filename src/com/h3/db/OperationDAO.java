package com.h3.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.h3.model.Operation;
import com.h3.util.DBUtils;

public class OperationDAO {

	private static final SimpleDateFormat mySqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<Operation> getAll() {
		return DBUtils.query("Select * from operations", Operation.class);
	}

	public List<Operation> getAllOfIsShow() {
		return DBUtils.query("Select * from operations where isShow=1", Operation.class);
	}

	public List<Operation> getAllOfDate(Date date) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);

		gregorianCalendar.set(Calendar.HOUR, 0);
		gregorianCalendar.set(Calendar.MINUTE, 0);
		gregorianCalendar.set(Calendar.SECOND, 0);
		gregorianCalendar.set(Calendar.MILLISECOND, 0);

		Date startOfDay = gregorianCalendar.getTime();

		gregorianCalendar.add(Calendar.DATE, 1);

		Date endOfDay = gregorianCalendar.getTime();

		return DBUtils.query("Select * from operations where startTime >= ? And endTime < ?", Operation.class, mySqlDateFormat.format(startOfDay), mySqlDateFormat.format(endOfDay));
	}

	public void add(Operation operation) {
		DBUtils.executeUpdate("Insert into operations(patientId, patientName, patientAge, operatingRoom, status, note, startTime, endTime, mainSurgeon, isShow) values(?,?,?,?,?,?,?,?,?,?)", operation.getPatientId(), operation.getPatientName(), operation.getPatientAge(), operation.getOperatingRoom(), operation.getStatus(), operation.getNote(), operation.getStartTime(), operation.getEndTime(), operation.getMainSurgeon(), operation.getIsShow());
	}

	public void update(Operation operation) {
		if (operation.getId() >= 0) {
			DBUtils.executeUpdate("Update operations set patientId = ?, patientName = ?, patientAge = ?, operatingRoom = ?, status = ?, note = ?, startTime = ?, endTime = ?, mainSurgeon = ?, isShow = ? where id = ?", operation.getPatientId(), operation.getPatientName(), operation.getPatientAge(), operation.getOperatingRoom(), operation.getStatus(), operation.getNote(), operation.getStartTime(), operation.getEndTime(), operation.getMainSurgeon(), operation.getIsShow(), operation.getId());
		}
	}

}
