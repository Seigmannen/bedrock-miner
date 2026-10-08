# Bedrock Miner

A Fabric mod for Minecraft 26.3 that lets you mine bedrock with a netherite pickaxe.

## What it does

- A **netherite pickaxe** held in your main hand can break **bedrock**. No other tool can.
- Broken bedrock **drops as a bedrock block** you can pick up and place again.
- This works on **all bedrock**, including the bottom layer of the Overworld (y = −64).
  Careful: breaking that layer opens a hole into the void.

## Mining time

Mining one bedrock block takes **2 minutes** with no upgrades.

| Bonus | Effect on mining time |
| --- | --- |
| Efficiency enchantment | −15 seconds per level |
| Haste effect | −20 seconds per level |
| Minimum time | never less than 5 seconds |

Formula: `time = 120 − 15 × Efficiency level − 20 × Haste level` seconds, but at least 5 seconds.

Examples:

| Pickaxe and effects | Time |
| --- | --- |
| No enchantments, no Haste | 120 s |
| Efficiency V | 45 s |
| Haste II (e.g. from a beacon) | 80 s |
| Efficiency V + Haste I | 25 s |
| Efficiency V + Haste II | 5 s |

The time is fixed by these rules only. Unlike normal blocks, mining bedrock is **not** slowed down
by Mining Fatigue, being underwater or not standing on the ground.

## Durability

- Each bedrock block costs the pickaxe **10 durability** instead of the usual 1.
- **Unbreaking works as normal**: it is rolled separately for each of the 10 points.
  On average a block costs about 5 durability with Unbreaking I, 3.3 with Unbreaking II
  and 2.5 with Unbreaking III.
- The pickaxe can break while mining bedrock, just like any other tool.

## Creative mode

In Creative mode bedrock is broken instantly as usual. It does not drop as an item,
and the pickaxe takes no damage.

## Requirements

| Software | Version |
| --- | --- |
| Minecraft (Java Edition) | 26.3 |
| Fabric Loader | 0.19.5 or newer |
| Fabric API | for 26.3 (built with 0.162.0+26.3) |
| Java | 25 or newer |

## Installation

1. Install the [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 26.3.
2. Download [Fabric API](https://modrinth.com/mod/fabric-api) for 26.3.
3. Put the Fabric API jar and the Bedrock Miner jar (`bedrock-miner-1.0.0.jar`) in your `mods` folder.
   - Windows: `%appdata%\.minecraft\mods`
4. Start Minecraft with the Fabric profile.

**Multiplayer:** the mod must be installed on **both the server and every player's client**.
The client and server have to agree on how fast bedrock breaks.

## Building from source

```
./gradlew build
```

The mod jar ends up in `build/libs/bedrock-miner-1.0.0.jar`
(the file ending in `-sources.jar` contains only the source code).

To test in a development game, run `./gradlew runClient`.

## License

This mod is available under the [MIT License](LICENSE).
