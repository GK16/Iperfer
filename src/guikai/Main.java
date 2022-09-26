package guikai;


public class Main {

    public static void main(String[] args) {
        // preliminary parameter check
        if(args.length != 3 && args.length != 7) {
            System.err.println("Error: invalid arguments1");
            System.exit(0);
        }
        if(!args[0].equals("-c") && !args[0].equals("-s")) {
            System.err.println("Error: invalid arguments2");
            System.exit(0);
        }

        // Client Mode
        if(args[0].equals("-c")) {
            if (args.length != 7 || !args[1].equals("-h") || !args[3].equals("-p") || !args[5].equals("-t")){
                System.err.println("Error: invalid arguments3");
                System.exit(0);
            }

            int portNum = 0;
            int time = 0;
            try {
                portNum = Integer.parseInt(args[4]);
                time = Integer.parseInt(args[6]);
            } catch (NumberFormatException e){
                // time or port is not number
                System.err.println("Error: invalid arguments4");
                System.err.println(e);
                System.exit(0);
            }

            if(portNum < 1024 || portNum > 65535){
                // port is out of range
                System.err.println("Error: port number must be in the range 1024 to 65535");
                System.exit(0);
            }

            Client client = new Client(args[2],portNum,time);
        }

        System.out.println("success!");

    }
}
