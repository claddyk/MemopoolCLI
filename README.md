
# MemopoolCLI


There's this great website that lets you explore the bitcoin blockchain (recent blocks and transactions) [Mempool](https://mempool.space/). 

It has an [API](https://mempool.space/docs/api/rest).

We are creating a CLI tool that calls their [get-blocks API](https://mempool.space/docs/api/rest#get-blocks)
It takes the startHeight field as a parameter and returns to the user the blockId and the list of all transactions-Id in that block, and discards the other information.
## Run Locally

Clone the project

```bash
  git clone https://github.com/claddyk/MemPoolCLI
```

## Support

For support, email madscientists1523@gmail.com.

