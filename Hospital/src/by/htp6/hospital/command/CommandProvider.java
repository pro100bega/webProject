package by.htp6.hospital.command;

import java.util.HashMap;

import java.util.Map;

import by.htp6.hospital.command.impl.SignUpCommand;
import by.htp6.hospital.command.impl.SignInCommand;
import by.htp6.hospital.command.impl.SignOutCommand;
import by.htp6.hospital.command.impl.AddNewPatientCommand;
import by.htp6.hospital.command.impl.AddAddpointmentCommand;
import by.htp6.hospital.command.impl.DischargePatientCommand;
import by.htp6.hospital.command.impl.EditPatientCommand;
import by.htp6.hospital.command.impl.GetPatientListCommand;
import by.htp6.hospital.command.impl.GetReportsCommand;
import by.htp6.hospital.command.impl.GetSingleReportCommand;
import by.htp6.hospital.command.impl.FindLogCommand;
import by.htp6.hospital.command.impl.FindPatientCommand;
import by.htp6.hospital.command.impl.GetAdministratorInfoCommand;
import by.htp6.hospital.command.impl.GetEditPatientPageCommand;
import by.htp6.hospital.command.impl.GetPatientInfoCommand;
import by.htp6.hospital.command.impl.PerformAppointmentCommand;
import by.htp6.hospital.command.impl.SendReportCommand;
import by.htp6.hospital.command.impl.SetLocaleCommand;

public class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();
	
	private Map<String, Command> commands = new HashMap<>();
	
	private CommandProvider(){
		commands.put("SIGN_IN", new SignInCommand());
		commands.put("SIGN_UP", new SignUpCommand());
		commands.put("SIGN_OUT", new SignOutCommand());
		commands.put("SET_LOCALE", new SetLocaleCommand());
		commands.put("GET_PATIENT_LIST", new GetPatientListCommand());
		commands.put("DISCHARGE_PATIENT", new DischargePatientCommand());
		commands.put("FIND_PATIENT", new FindPatientCommand());
		commands.put("ADD_NEW_PATIENT", new AddNewPatientCommand());
		commands.put("GET_ADMIN_INFO", new GetAdministratorInfoCommand());
		commands.put("FIND_LOG", new FindLogCommand());
		commands.put("GET_PATIENT_INFO", new GetPatientInfoCommand());
		commands.put("ADD_APPOINTMENT", new AddAddpointmentCommand());
		commands.put("PERFORM_APPOINTMENT", new PerformAppointmentCommand());
		commands.put("GET_EDIT_PATIENT_PAGE", new GetEditPatientPageCommand());
		commands.put("EDIT_PATIENT", new EditPatientCommand());
		commands.put("SEND_REPORT", new SendReportCommand());
		commands.put("GET_REPORTS", new GetReportsCommand());
		commands.put("GET_SINGLE_REPORT", new GetSingleReportCommand());
	}
	
	public static CommandProvider getInstance(){
		return instance;
	}
	
	public Command getCommand(String commandName){
		Command command;
		command = commands.get(commandName);
		return command;
	}
	
}
