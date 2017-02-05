package se.osbe.emoreactor.terminal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StartTerminal {

	public StartTerminal(String[] args) {
		
		Map<String, String> cmdTab = new HashMap<String, String>();
		
		for (int i = 0; i < args.length; i += 2) {
			int n = i + 1;
			String cmd = i < args.length ? args[i] : "N/A";
			String prm = n < args.length ? args[n] : "N/A";
			cmdTab.put("" + cmd, "" + prm);
		}
		
		Iterator<String> i = cmdTab.keySet().iterator();
		
		while(i.hasNext()) {
			TerminalCommands cmd = null;
			String candidate = i.next();
			try {
				cmd = TerminalCommands.valueOf(candidate.toUpperCase());
			} catch(Exception e) {
				System.err.println("No such command: " + candidate);
			}
			if(cmd != null) {
				System.out.println(cmd);
			}
		}
		System.out.println(cmdTab);
	}

	public static void main(String[] args) {
		new StartTerminal(args);
	}
}
