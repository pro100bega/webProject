package by.htp6.hospital.command;

import java.util.HashMap;
import java.util.Map;

import by.htp6.hospital.command.impl.BookUserCommand;
import by.htp6.hospital.command.impl.LogInUserCommand;

public class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();
	
	private Map<String, Command> commands = new HashMap<>();
	
	private CommandProvider(){
		commands.put("LOGIN_USER", new LogInUserCommand());
		commands.put("BOOK_USER", new BookUserCommand());
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
