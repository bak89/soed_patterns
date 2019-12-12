/**
 * Copyright (C) 2017-2019 Eric Dubuis, Berner Fachhochschule <due1@bfh.ch>
 *
 * Software Engineering and Design
 */
package ch.bfh.due1.dp.template;

public class BufferDemo {
	private final static int DEFAULTBUFSIZE = 5;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String usage = "Usage: java ch.bfh.due1.dp.template.BufferDemo " + "[-verbose] [-bufclass fqclassname] [-bufsize n] "
				+ "[-prodcnt pcnt] [-conscnt ccnt] count";
		int i = 0;
		String arg;
		boolean vflag = false;
		String classname = null;
		int bufsize = DEFAULTBUFSIZE;
		int m = 0;
		int prodcnt = 1, conscnt = 1;
		Buffer<Item> buf = null;

		while (i < args.length && args[i].startsWith("-")) {
			arg = args[i++];
			if (arg.equals("-verbose")) {
				vflag = true;
			} else if (arg.equals("-bufclass")) {
				if (i < args.length) {
					classname = args[i++];
				}
			} else if (arg.equals("-bufsize")) {
				if (i < args.length) {
					try {
						bufsize = (new Integer(args[i++])).intValue();
					} catch (java.lang.NumberFormatException e) {
						System.out.println("Illegal bounds specification.");
						System.out.println("Using " + DEFAULTBUFSIZE + ".");
					}
				}
			} else if (arg.equals("-prodcnt")) {
				if (i < args.length) {
					try {
						prodcnt = (new Integer(args[i++])).intValue();
					} catch (java.lang.NumberFormatException e) {
						System.out.println("Illegal prodcnt specification.");
						System.out.println(usage);
						System.exit(1);
					}
				}
			} else if (arg.equals("-conscnt")) {
				if (i < args.length) {
					try {
						conscnt = (new Integer(args[i++])).intValue();
					} catch (java.lang.NumberFormatException e) {
						System.out.println("Illegal conscnt specification.");
						System.out.println(usage);
						System.exit(1);
					}
				}
			}
		}
		if (i == args.length) {
			System.out.println(usage);
			System.exit(1);
		} else {
			try {
				m = (new Integer(args[i])).intValue();
			} catch (java.lang.NumberFormatException e) {
				System.out.println("Illegal count specification.");
				System.out.println(usage);
				System.exit(1);
			}
		}

		// Create the specified buffer. If not specified,
		// a one-place buffer is used.
		Class<Buffer<Item>> bufclass;
		if (classname != null) {
			try {
				bufclass = (Class<Buffer<Item>>) Class.forName(classname);
				buf = bufclass.newInstance();
				buf.init(bufsize);
				System.out.println("Using " + bufclass.getName());
			} catch (ClassNotFoundException e) {
				System.out.println("Could not find class " + classname + ".");
				System.out.println("Use fully qualified class name.");
				System.exit(1);
			} catch (IllegalArgumentException e) {
				System.out.println("WARNING: Buffer size initialization argument " + bufsize + " rejected.");
				System.out.println("Using " + DEFAULTBUFSIZE + ".");
				buf.init(DEFAULTBUFSIZE);
			} catch (Exception e) {
				System.out.println("Could not instantiate class " + classname + ".");
				e.printStackTrace();
				System.exit(1);
			}
		}
		else {
			try {
				bufclass = (Class<Buffer<Item>>) Class.forName("ch.bfh.due1.dp.template.OnePlaceBuffer");
				buf = bufclass.newInstance();
				if (!vflag)
					System.out.println("Using ch.bfh.due1.dp.template.OnePlaceBuffer.");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not find class " + classname + ".");
				System.out.println("Use fully qualified class name.");
				System.exit(1);
			} catch (InstantiationException e) {
				System.out.println("Could not instantiate class " + classname + ".");
				System.exit(1);
			} catch (Exception e) {
				System.out.println("Could not instantiate class " + classname + ".");
				e.printStackTrace();
				System.exit(1);
			}
		}

		// Create and start the threads.
		for (i = 0; i < conscnt; i++) {
			new ItemConsumer(i, m, buf, vflag);
		}
		for (i = conscnt; i < (conscnt + prodcnt); i++) {
			new ItemProducer(i - conscnt, m, buf, vflag);
		}
	}
}
