package bohdan.hushcha.sushchak.suggestme.rest.models.CryptoCurrency;

import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {

    @SerializedName("id")
    private String Id;
    @SerializedName("name")
    private String Name;
    @SerializedName("symbol")
    private String Symbol;
    @SerializedName("rank")
    private Integer Rank;
    @SerializedName("price_usd")
    private String PriceUSD;
    @SerializedName("price_btc")
    private String PriceBTC;
    @SerializedName("24h_volume_usd")
    private String Volume24;
    @SerializedName("market_cap_usd")
    private String MarketCapUSD;
    @SerializedName("available_supply")
    private String AvailableSupply;
    @SerializedName("total_supply")
    private String TotalSupply;
    @SerializedName("max_supply")
    private String MaxSupply;
    @SerializedName("percent_change_1h")
    private String PercentChange1h;
    @SerializedName("percent_change_24h")
    private String PercentChange24h;
    @SerializedName("percent_change_7d")
    private String PercentChange7d;
    @SerializedName("last_updated")
    private String LastUpdate;

    public CryptoCurrency(String id, String name,
                          String symbol,
                          Integer rank, String priceUSD,
                          String priceBTC, String volume24,
                          String marketCapUSD, String availableSupply,
                          String totalSupply,
                          String maxSupply, String percentChange1h,
                          String percentChange24h, String percentChange7d,
                          String lastUpdate) {
        this.Id = id;
        this.Name = name;
        this.Symbol = symbol;
        this.Rank = rank;
        this.PriceUSD = priceUSD;
        this.PriceBTC = priceBTC;
        this.Volume24 = volume24;
        this.MarketCapUSD = marketCapUSD;
        this.AvailableSupply = availableSupply;
        this.TotalSupply = totalSupply;
        this.MaxSupply = maxSupply;
        this.PercentChange1h = percentChange1h;
        this.PercentChange24h = percentChange24h;
        this.PercentChange7d = percentChange7d;
        this.LastUpdate = lastUpdate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer rank) {
        Rank = rank;
    }

    public String getPriceUSD() {
        return PriceUSD;
    }

    public void setPriceUSD(String priceUSD) {
        PriceUSD = priceUSD;
    }

    public String getPriceBTC() {
        return PriceBTC;
    }

    public void setPriceBTC(String priceBTC) {
        PriceBTC = priceBTC;
    }

    public String getVolume24() {
        return Volume24;
    }

    public void setVolume24(String volume24) {
        Volume24 = volume24;
    }

    public String getMarketCapUSD() {
        return MarketCapUSD;
    }

    public void setMarketCapUSD(String marketCapUSD) {
        MarketCapUSD = marketCapUSD;
    }

    public String getAvailableSupply() {
        return AvailableSupply;
    }

    public void setAvailableSupply(String availableSupply) {
        AvailableSupply = availableSupply;
    }

    public String getTotalSupply() {
        return TotalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        TotalSupply = totalSupply;
    }

    public String getMaxSupply() {
        return MaxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        MaxSupply = maxSupply;
    }

    public String getPercentChange1h() {
        return PercentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        PercentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        return PercentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        PercentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        return PercentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        PercentChange7d = percentChange7d;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }
}
