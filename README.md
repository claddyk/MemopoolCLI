
# MemopoolCLI

There's this great website that lets you explore the bitcoin blockchain (recent blocks and transactions) [Mempool](https://mempool.space/). 

It has an [API](https://mempool.space/docs/api/rest).

We are creating a CLI tool that calls their [get-blocks API](https://mempool.space/docs/api/rest#get-blocks)
It takes the startHeight field as a parameter and returns to the user the blockId and the list of all transactions-Id in that block, and discards the other information.

### Prerequisites

Before building and running the project, ensure that you have the following prerequisites installed on your system:

* Kotlin
* Gradle


## Build and Run Locally

### Building the CLI Tool
* Clone the project

```bash
  git clone https://github.com/claddyk/MempoolCLI.git
```

* Go to the project directory

```bash
  cd MempoolCLI
```

* Build the project using Gradle:

```bash
  gradle build
```
This command compiles the source code, runs tests, and generates the executable JAR file.

### Running the CLI Tool

Once you have built the project, you can run the CLI tool using the following command:

```bash
  java -jar build/libs/your-project.jar [command] [options]
```
Replace your-project.jar with the actual JAR file name generated during the build process.

Replace [command] and [options] with the appropriate command and options to execute the desired functionality of the CLI tool.

We have the following two commands:
* fetchBlockId - used to return the blockId for the given value of startHeight
* fetchTxIds - used to return all the Transaction Ids in the particular block, whose startHeight is provided as input

The options are of adding value of startHeight, whose shortName is s.

For example,
```bash
    java -jar build/libs/your-project.jar fetchBlockId -s 12345
```

### Running tests
To execute the project tests, use the following command:

```bash
    gradle test
```

That's it! You should now be able to build the project and run the CLI tool with the specified commands and options.


## Support

For support, email madscientists1523@gmail.com.
