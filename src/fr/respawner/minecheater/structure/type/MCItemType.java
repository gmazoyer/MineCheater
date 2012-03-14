/*
 * Copyright (c) 2012 Guillaume Mazoyer
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.respawner.minecheater.structure.type;

public enum MCItemType {
    UNKNOWN,
    IRON_SHOVEL,
    IRON_PICKAXE,
    IRON_AXE,
    FLINT_AND_STEEL,
    RED_APPLE,
    BOW,
    ARROW,
    COAL,
    DIAMOND,
    IRON_INGOT,
    GOLD_INGOT,
    IRON_SWORD,
    WOODEN_SWORD,
    WOODEN_SHOVEL,
    WOODEN_PICKAXE,
    WOODEN_AXE,
    STONE_SWORD,
    STONE_SHOVEL,
    STONE_PICKAXE,
    STONE_AXE,
    DIAMOND_SWORD,
    DIAMOND_SHOVEL,
    DIAMOND_PICKAXE,
    DIAMOND_AXE,
    STICK,
    BOWL,
    MUSHROOM_SOUP,
    GOLD_SWORD,
    GOLD_SHOVEL,
    GOLD_PICKAXE,
    GOLD_AXE,
    STRING,
    FEATHER,
    GUNPOWDER,
    WOODEN_HOE,
    STONE_HOE,
    IRON_HOE,
    DIAMOND_HOE,
    GOLD_HOE,
    SEEDS,
    WHEAT,
    BREAD,
    LEATHER_CAP,
    LEATHER_TUNIC,
    LEATHER_PANTS,
    LEATHER_BOOTS,
    CHAIN_HELMET,
    CHAIN_CHESTPLATE,
    CHAIN_LEGGINGS,
    CHAIN_BOOTS,
    IRON_HELMET,
    IRON_CHESTPLATE,
    IRON_LEGGINGS,
    IRON_BOOTS,
    DIAMOND_HELMET,
    DIAMOND_CHESTPLATE,
    DIAMOND_LEGGINGS,
    DIAMOND_BOOTS,
    GOLD_HELMET,
    GOLD_CHESTPLATE,
    GOLD_LEGGINGS,
    GOLD_BOOTS,
    FLINT,
    RAW_PORKCHOP,
    COOKED_PORKCHOP,
    PAINTINGS,
    GOLDEN_APPLE,
    SIGN,
    WOODEN_DOOR,
    BUCKET,
    WATER_BUCKET,
    LAVA_BUCKET,
    MINECART,
    SADDLE,
    IRON_DOOR,
    REDSTONE,
    SNOWBALL,
    BOAT,
    LEATHER,
    MILK,
    CLAY_BRICK,
    CLAY,
    SUGAR_CANE,
    PAPER,
    BOOK,
    SLIMEBALL,
    MINECART_WITH_CHEST,
    MINECART_WITH_FURNACE,
    CHICKEN_EGG,
    COMPASS,
    FISHING_ROD,
    CLOCK,
    GLOWSTONE_DUST,
    RAW_FISH,
    COOKED_FISH,
    DYE,
    BONE,
    SUGAR,
    CAKE,
    BED,
    REDSTONE_REPEATER,
    COOKIE,
    MAP,
    SHEARS,
    MELON_SLICE,
    PUMPKIN_SEEDS,
    MELON_SEEDS,
    RAW_BEEF,
    STEAK,
    RAW_CHICKEN,
    COOKED_CHICKEN,
    ROTTEN_FLESH,
    ENDER_PEARL,
    BLAZE_ROD,
    GHAST_TEAR,
    GOLD_NUGGET,
    NETHER_WART,
    POTIONS,
    GLASS_BOTTLE,
    SPIDER_EYE,
    FERMENTED_SPIDER_EYE,
    BLAZE_POWDER,
    MAGMA_CREAM,
    BREWING_STAND,
    CAULDRON,
    EYE_OF_ENDER,
    GLISTERING_MELON,
    SPAWN_EGG,
    BOTTLE_O_ENCHANTING,
    FIRE_CHARGE,
    DISC_13,
    CAT_DISC,
    BLOCKS_DISC,
    CHIRP_DISC,
    FAR_DISC,
    MALL_DISC,
    MELLOHI_DISC,
    STAL_DISC,
    STRAD_DISC,
    WARD_DISC,
    DISC_11;

    public static MCItemType itemForID(short id) {
        switch (id) {
        case 256:
            return IRON_SHOVEL;
        case 257:
            return IRON_PICKAXE;
        case 258:
            return IRON_AXE;
        case 259:
            return FLINT_AND_STEEL;
        case 260:
            return RED_APPLE;
        case 261:
            return BOW;
        case 262:
            return ARROW;
        case 263:
            return COAL;
        case 264:
            return DIAMOND;
        case 265:
            return IRON_INGOT;
        case 266:
            return GOLD_INGOT;
        case 267:
            return IRON_SWORD;
        case 268:
            return WOODEN_SWORD;
        case 269:
            return WOODEN_SHOVEL;
        case 270:
            return WOODEN_PICKAXE;
        case 271:
            return WOODEN_AXE;
        case 272:
            return STONE_SWORD;
        case 273:
            return STONE_SHOVEL;
        case 274:
            return STONE_PICKAXE;
        case 275:
            return STONE_AXE;
        case 276:
            return DIAMOND_SWORD;
        case 277:
            return DIAMOND_SHOVEL;
        case 278:
            return DIAMOND_PICKAXE;
        case 279:
            return DIAMOND_AXE;
        case 280:
            return STICK;
        case 281:
            return BOWL;
        case 282:
            return MUSHROOM_SOUP;
        case 283:
            return GOLD_SWORD;
        case 284:
            return GOLD_SHOVEL;
        case 285:
            return GOLD_PICKAXE;
        case 286:
            return GOLD_AXE;
        case 287:
            return STRING;
        case 288:
            return FEATHER;
        case 289:
            return GUNPOWDER;
        case 290:
            return WOODEN_HOE;
        case 291:
            return STONE_HOE;
        case 292:
            return IRON_HOE;
        case 293:
            return DIAMOND_HOE;
        case 294:
            return GOLD_HOE;
        case 295:
            return SEEDS;
        case 296:
            return WHEAT;
        case 297:
            return BREAD;
        case 298:
            return LEATHER_CAP;
        case 299:
            return LEATHER_TUNIC;
        case 300:
            return LEATHER_PANTS;
        case 301:
            return LEATHER_BOOTS;
        case 302:
            return CHAIN_HELMET;
        case 303:
            return CHAIN_CHESTPLATE;
        case 304:
            return CHAIN_LEGGINGS;
        case 305:
            return CHAIN_BOOTS;
        case 306:
            return IRON_HELMET;
        case 307:
            return IRON_CHESTPLATE;
        case 308:
            return IRON_LEGGINGS;
        case 309:
            return IRON_BOOTS;
        case 310:
            return DIAMOND_HELMET;
        case 311:
            return DIAMOND_CHESTPLATE;
        case 312:
            return DIAMOND_LEGGINGS;
        case 313:
            return DIAMOND_BOOTS;
        case 314:
            return GOLD_HELMET;
        case 315:
            return GOLD_CHESTPLATE;
        case 316:
            return GOLD_LEGGINGS;
        case 317:
            return GOLD_BOOTS;
        case 318:
            return FLINT;
        case 319:
            return RAW_PORKCHOP;
        case 320:
            return COOKED_PORKCHOP;
        case 321:
            return PAINTINGS;
        case 322:
            return GOLDEN_APPLE;
        case 323:
            return SIGN;
        case 324:
            return WOODEN_DOOR;
        case 325:
            return BUCKET;
        case 326:
            return WATER_BUCKET;
        case 327:
            return LAVA_BUCKET;
        case 328:
            return MINECART;
        case 329:
            return SADDLE;
        case 330:
            return IRON_DOOR;
        case 331:
            return REDSTONE;
        case 332:
            return SNOWBALL;
        case 333:
            return BOAT;
        case 334:
            return LEATHER;
        case 335:
            return MILK;
        case 336:
            return CLAY_BRICK;
        case 337:
            return CLAY;
        case 338:
            return SUGAR_CANE;
        case 339:
            return PAPER;
        case 340:
            return BOOK;
        case 341:
            return SLIMEBALL;
        case 342:
            return MINECART_WITH_CHEST;
        case 343:
            return MINECART_WITH_FURNACE;
        case 344:
            return CHICKEN_EGG;
        case 345:
            return COMPASS;
        case 346:
            return FISHING_ROD;
        case 347:
            return CLOCK;
        case 348:
            return GLOWSTONE_DUST;
        case 349:
            return RAW_FISH;
        case 350:
            return COOKED_FISH;
        case 351:
            return DYE;
        case 352:
            return BONE;
        case 353:
            return SUGAR;
        case 354:
            return CAKE;
        case 355:
            return BED;
        case 356:
            return REDSTONE_REPEATER;
        case 357:
            return COOKIE;
        case 358:
            return MAP;
        case 359:
            return SHEARS;
        case 360:
            return MELON_SLICE;
        case 361:
            return PUMPKIN_SEEDS;
        case 362:
            return MELON_SEEDS;
        case 363:
            return RAW_BEEF;
        case 364:
            return STEAK;
        case 365:
            return RAW_CHICKEN;
        case 366:
            return COOKED_CHICKEN;
        case 367:
            return ROTTEN_FLESH;
        case 368:
            return ENDER_PEARL;
        case 369:
            return BLAZE_ROD;
        case 370:
            return GHAST_TEAR;
        case 371:
            return GOLD_NUGGET;
        case 372:
            return NETHER_WART;
        case 373:
            return POTIONS;
        case 374:
            return GLASS_BOTTLE;
        case 375:
            return SPIDER_EYE;
        case 376:
            return FERMENTED_SPIDER_EYE;
        case 377:
            return BLAZE_POWDER;
        case 378:
            return MAGMA_CREAM;
        case 379:
            return BREWING_STAND;
        case 380:
            return CAULDRON;
        case 381:
            return EYE_OF_ENDER;
        case 382:
            return GLISTERING_MELON;
        case 383:
            return SPAWN_EGG;
        case 384:
            return BOTTLE_O_ENCHANTING;
        case 385:
            return FIRE_CHARGE;
        case 2256:
            return DISC_13;
        case 2257:
            return CAT_DISC;
        case 2258:
            return BLOCKS_DISC;
        case 2259:
            return CHIRP_DISC;
        case 2260:
            return FAR_DISC;
        case 2261:
            return MALL_DISC;
        case 2262:
            return MELLOHI_DISC;
        case 2263:
            return STAL_DISC;
        case 2264:
            return STRAD_DISC;
        case 2265:
            return WARD_DISC;
        case 2266:
            return DISC_11;
        default:
            return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case IRON_SHOVEL:
            return "Iron Shovel";
        case IRON_PICKAXE:
            return "Iron Pickaxe";
        case IRON_AXE:
            return "Iron Axe";
        case FLINT_AND_STEEL:
            return "Flint and Steel";
        case RED_APPLE:
            return "Red Apple";
        case BOW:
            return "Bow";
        case ARROW:
            return "Arrow";
        case COAL:
            return "Coal";
        case DIAMOND:
            return "Diamond";
        case IRON_INGOT:
            return "Iron Ingot";
        case GOLD_INGOT:
            return "Gold Ingot";
        case IRON_SWORD:
            return "Iron Sword";
        case WOODEN_SWORD:
            return "Wooden Sword";
        case WOODEN_SHOVEL:
            return "Wooden Shovel";
        case WOODEN_PICKAXE:
            return "Wooden Pickaxe";
        case WOODEN_AXE:
            return "Wooden Axe";
        case STONE_SWORD:
            return "Stone Sword";
        case STONE_SHOVEL:
            return "Stone Shovel";
        case STONE_PICKAXE:
            return "Stone Pickaxe";
        case STONE_AXE:
            return "Stone Axe";
        case DIAMOND_SWORD:
            return "Diamond Sword";
        case DIAMOND_SHOVEL:
            return "Diamond Shovel";
        case DIAMOND_PICKAXE:
            return "Diamond Pickaxe";
        case DIAMOND_AXE:
            return "Diamond Axe";
        case STICK:
            return "Stick";
        case BOWL:
            return "Bowl";
        case MUSHROOM_SOUP:
            return "Mushroom Soup";
        case GOLD_SWORD:
            return "Gold Sword";
        case GOLD_SHOVEL:
            return "Gold Shovel";
        case GOLD_PICKAXE:
            return "Gold Pickaxe";
        case GOLD_AXE:
            return "Gold Axe";
        case STRING:
            return "String";
        case FEATHER:
            return "Feather";
        case GUNPOWDER:
            return "Gunpowder";
        case WOODEN_HOE:
            return "Wooden Hoe";
        case STONE_HOE:
            return "Stone Hoe";
        case IRON_HOE:
            return "Iron Hoe";
        case DIAMOND_HOE:
            return "Diamond Hoe";
        case GOLD_HOE:
            return "Gold Hoe";
        case SEEDS:
            return "Seeds";
        case WHEAT:
            return "Wheat";
        case BREAD:
            return "Bread";
        case LEATHER_CAP:
            return "Leather Cap";
        case LEATHER_TUNIC:
            return "Leather Tunic";
        case LEATHER_PANTS:
            return "Leather Pants";
        case LEATHER_BOOTS:
            return "Leather Boots";
        case CHAIN_HELMET:
            return "Chain Helmet";
        case CHAIN_CHESTPLATE:
            return "Chain Chestplate";
        case CHAIN_LEGGINGS:
            return "Chain Leggings";
        case CHAIN_BOOTS:
            return "Chain Boots";
        case IRON_HELMET:
            return "Iron Helmet";
        case IRON_CHESTPLATE:
            return "Iron Chestplate";
        case IRON_LEGGINGS:
            return "Iron Leggings";
        case IRON_BOOTS:
            return "Iron Boots";
        case DIAMOND_HELMET:
            return "Diamond Helmet";
        case DIAMOND_CHESTPLATE:
            return "Diamond Chestplate";
        case DIAMOND_LEGGINGS:
            return "Diamond Leggings";
        case DIAMOND_BOOTS:
            return "Diamond Boots";
        case GOLD_HELMET:
            return "Gold Helmet";
        case GOLD_CHESTPLATE:
            return "Gold Chestplate";
        case GOLD_LEGGINGS:
            return "Gold Leggings";
        case GOLD_BOOTS:
            return "Gold Boots";
        case FLINT:
            return "Flint";
        case RAW_PORKCHOP:
            return "Raw Porkchop";
        case COOKED_PORKCHOP:
            return "Cooked Porkchop";
        case PAINTINGS:
            return "Paintings";
        case GOLDEN_APPLE:
            return "Golden Apple";
        case SIGN:
            return "Sign";
        case WOODEN_DOOR:
            return "Wooden Door";
        case BUCKET:
            return "Bucket";
        case WATER_BUCKET:
            return "Water Bucket";
        case LAVA_BUCKET:
            return "Lava Bucket";
        case MINECART:
            return "Minecart";
        case SADDLE:
            return "Saddle";
        case IRON_DOOR:
            return "Iron Door";
        case REDSTONE:
            return "Redstone";
        case SNOWBALL:
            return "Snowball";
        case BOAT:
            return "Boat";
        case LEATHER:
            return "Leather";
        case MILK:
            return "Milk";
        case CLAY_BRICK:
            return "Clay Brick";
        case CLAY:
            return "Clay";
        case SUGAR_CANE:
            return "Sugar Cane";
        case PAPER:
            return "Paper";
        case BOOK:
            return "Book";
        case SLIMEBALL:
            return "Slimeball";
        case MINECART_WITH_CHEST:
            return "Minecart with Chest";
        case MINECART_WITH_FURNACE:
            return "Minecart with Furnace";
        case CHICKEN_EGG:
            return "Chicken Egg";
        case COMPASS:
            return "Compass";
        case FISHING_ROD:
            return "Fishing Rod";
        case CLOCK:
            return "Clock";
        case GLOWSTONE_DUST:
            return "Glowstone Dust";
        case RAW_FISH:
            return "Raw Fish";
        case COOKED_FISH:
            return "Cooked Fish";
        case DYE:
            return "Dye";
        case BONE:
            return "Bone";
        case SUGAR:
            return "Sugar";
        case CAKE:
            return "Cake";
        case BED:
            return "Bed";
        case REDSTONE_REPEATER:
            return "Redstone Repeater";
        case COOKIE:
            return "Cookie";
        case MAP:
            return "Map";
        case SHEARS:
            return "Shears";
        case MELON_SLICE:
            return "Melon Slice";
        case PUMPKIN_SEEDS:
            return "Pumpkin Seeds";
        case MELON_SEEDS:
            return "Melon Seeds";
        case RAW_BEEF:
            return "Raw Beef";
        case STEAK:
            return "Steak";
        case RAW_CHICKEN:
            return "Raw Chicken";
        case COOKED_CHICKEN:
            return "Cooked Chicken";
        case ROTTEN_FLESH:
            return "Rotten Flesh";
        case ENDER_PEARL:
            return "Ender Pearl";
        case BLAZE_ROD:
            return "Blaze Rod";
        case GHAST_TEAR:
            return "Ghast Tear";
        case GOLD_NUGGET:
            return "Gold Nugget";
        case NETHER_WART:
            return "Nether Wart";
        case POTIONS:
            return "Potions";
        case GLASS_BOTTLE:
            return "Glass Bottle";
        case SPIDER_EYE:
            return "Spider Eye";
        case FERMENTED_SPIDER_EYE:
            return "Fermented Spider Eye";
        case BLAZE_POWDER:
            return "Blaze Powder";
        case MAGMA_CREAM:
            return "Magma Cream";
        case BREWING_STAND:
            return "Brewing Stand";
        case CAULDRON:
            return "Cauldron";
        case EYE_OF_ENDER:
            return "Eye of Ender";
        case GLISTERING_MELON:
            return "Glistering Melon";
        case SPAWN_EGG:
            return "Spawn Egg";
        case BOTTLE_O_ENCHANTING:
            return "Bottle o' Enchanting";
        case FIRE_CHARGE:
            return "Fire Charge";
        case DISC_13:
            return "Disc 13";
        case CAT_DISC:
            return "Cat Disc";
        case BLOCKS_DISC:
            return "Blocks Disc";
        case CHIRP_DISC:
            return "Chirp Disc";
        case FAR_DISC:
            return "Far Disc";
        case MALL_DISC:
            return "Mall Disc";
        case MELLOHI_DISC:
            return "Mellohi Disc";
        case STAL_DISC:
            return "Stal Disc";
        case STRAD_DISC:
            return "Strad Disc";
        case WARD_DISC:
            return "Ward Disc";
        case DISC_11:
            return "Disc 11";
        default:
            return "Unknown";
        }
    }
}
