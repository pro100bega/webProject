package by.htp6.hospital.constant;

/**
 * Предподготовленные запросы к базе данных
 * 
 * Prepared database queries
 * 
 * @author Begench Shamuradov, 2016
 */
public class SqlQuery {

	public static final String ADD_APPOINTMENT = "INSERT INTO appointment"
			+ "(patient_id, doctor_id, type, name, status, appointment_term) VALUES (?,?,?,?,?,?)";
}
