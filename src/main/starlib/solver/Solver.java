package starlib.solver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import starlib.GlobalVariables;
import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Formula;
import starlib.formula.PureFormula;
import starlib.formula.Utilities;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredMap;

public class Solver {
	
	private static int count = 0;
	
	private static String s2sat = "s2sat";

	private static boolean ret = false;
	
	private static boolean retEntail = false;
	
	private static StringBuilder model = new StringBuilder();
	
	private static Process p = null;
	
	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public static void terminate() {
		executor.shutdown();
		/*
		try {
		    if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
		    	executor.shutdownNow();
		    } 
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
		//*/
	}
	
	public static boolean checkEntail(Formula lhs, Formula rhs) {
		retEntail = false;
		
		File file = printToFile(lhs, rhs);
		
		if (file != null) {
			boolean ret = checkEntail(file);
			return ret;
		}
		
		return false;
	}
	
	private static File printToFile(Formula lhs, Formula rhs) {
		try {
			File file = File.createTempFile("entail", null);
//			System.out.println(file.getAbsolutePath());
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));

			DataNode[] dns = DataNodeMap.getAll();
			for (int i = 0; i < dns.length; i++) {
				String dn = dns[i].toS2SATString();
				bw.write(dn + "\n");
			}

			InductivePred[] preds = InductivePredMap.getAll();
			for (int i = 0; i < preds.length; i++) {
				String pred = preds[i].toS2SATString();
				bw.write(pred + "\n");
			}

			String problem = "checkentail " + lhs.toS2SATString() + " |- " + rhs.toS2SATString() +  ".\n";
			bw.write(problem);

			bw.flush();
			bw.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	private static boolean checkEntail(File file) {
		try {
			Future future = null;
			String cmd = s2sat + " " + file.getAbsolutePath();
			
			Runnable check = new Thread() {
				@Override
				public void run() {
					try {
						p = Runtime.getRuntime().exec(cmd);
						
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

						String s = br.readLine();
						
						while (s != null) {
							if (s.contains("Valid")) {
								retEntail = true;
								break;
							}
							
							if (s.contains("Fail")) {
								retEntail = false;
								break;
							}
							
							s = br.readLine();
						}
						
						br.close();
						p.waitFor();
					} catch (Exception e) {

					}
				}
			};
			
			if(executor.isShutdown()) {
				executor = Executors.newSingleThreadExecutor();
			}

			future = executor.submit(check);
			
			/*
			int maxTime = MAX_TIME;
			
			String s = c.getProperty("star.max_time");
			if (s != null) {
				maxTime = Integer.parseInt(s);
			}
			//*/
			
			future.get(GlobalVariables.MAX_TIME, TimeUnit.SECONDS);

			return retEntail;
		} catch (Exception e) {
			retEntail = false;
			if (p.isAlive()) p.destroyForcibly();
				
			return false;
		}
	}
	
	
	
	public static boolean checkSat(List<Formula> fs) {
//		System.out.println(fs);
		for (Formula f : fs) {
//			System.out.println(f);
			if (checkSat(f)) {
//				System.out.println("true");
				return true;
			} else {
//				System.out.println("false");
			}
		}
		
		return false;
	}
	
	public static boolean checkSat(Formula f) {
//		System.out.println(f);
//		System.out.println(f.getDepth());
		
		ret = false; model = new StringBuilder();

		if (f.getDepth() > GlobalVariables.MAX_DEPTH) {
			return false;
		} else {
			count++;
			
			// return true;
			File file = printToFile(f);
			
			if (file != null) {
				boolean ret = checkSat(file);
				return ret;
			}
			
			return false;
		}
	}

	private static File printToFile(Formula f) {
		try {
			File file = File.createTempFile("sat", null);
//			System.out.println(file.getAbsolutePath());
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));

			DataNode[] dns = DataNodeMap.getAll();
			for (int i = 0; i < dns.length; i++) {
				String dn = dns[i].toS2SATString();
				bw.write(dn + "\n");
			}

			InductivePred[] preds = InductivePredMap.getAll();
			for (int i = 0; i < preds.length; i++) {
				String pred = preds[i].toS2SATString();
				bw.write(pred + "\n");
			}

			String problem = "checksat " + f.toS2SATString() + ".\n";
			bw.write(problem);

			bw.flush();
			bw.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	private static boolean checkSat(File file) {
		try {
			Future future = null;
			String cmd = s2sat + " " + file.getAbsolutePath();
			
			Runnable check = new Thread() {
				@Override
				public void run() {
					try {
						p = Runtime.getRuntime().exec(cmd);
						
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

						String s = br.readLine();
						
						boolean readModel = false;
						while (s != null) {
							// System.out.println(s);

							if (s.contains("cex:")) {
								readModel = true;
							}

							if (s.contains(": SAT")) {
								ret = true;
								readModel = false;
								break;
							} else if (s.contains(": UNSAT")) {
								ret = false; model = new StringBuilder();
								readModel = false;
								break;
							}

							if (readModel) {
								if (s.contains("cex:"))
									model.append(s.substring(s.indexOf("cex:")));
								else if (s.contains("Pure Assigment"))
									model.append(";" + s);
								else if (!s.contains("true"))
									model.append(s);
							}

							s = br.readLine();
						}

						br.close();
						p.waitFor();
					} catch (Exception e) {

					}
				}
			};
			
			if(executor.isShutdown()) {
				executor = Executors.newSingleThreadExecutor();
			}

			future = executor.submit(check);
			
			/*
			int maxTime = MAX_TIME;
			
			String s = c.getProperty("star.max_time");
			if (s != null) {
				maxTime = Integer.parseInt(s);
			}
			//*/
			
			future.get(GlobalVariables.MAX_TIME, TimeUnit.SECONDS);

			return ret;
		} catch (Exception e) {
			ret = false; model = new StringBuilder();
			if (p.isAlive()) p.destroyForcibly();
				
			return false;
		}
	}

	public static String getModel() {
		return model.toString();
	}
	
	public static int getCount() {
		return count;
	}

}
