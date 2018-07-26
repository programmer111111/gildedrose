package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQuality(new GildedRoseItem(item));
        }
    }

    public void updateQuality(GildedRoseItem gildedRoseItem) {
        Item item = gildedRoseItem.item;
        if (isAgedBrie(item) || isBackstagePasses(item)) {
            incrementQuality(item);
            if (isBackstagePasses(item)) {
                if (item.sellIn < 11) {
                    incrementQuality(item);
                }

                if (item.sellIn < 6) {
                    incrementQuality(item);
                }
            }
        } else {
            if (!isSulfuras(item)) {
                decrementQuality(item);
            }
        }

        if (!isSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (isAgedBrie(item)) {
                incrementQuality(item);
            } else {
                if (isBackstagePasses(item)) {
                    item.quality = 0;
                } else {
                    if (!isSulfuras(item)) {
                        decrementQuality(item);
                    }
                }
            }
        }
    }

    public static void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    public static void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    public static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    public static boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    public static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
}