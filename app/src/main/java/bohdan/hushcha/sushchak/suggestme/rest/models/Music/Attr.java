package bohdan.hushcha.sushchak.suggestme.rest.models.Music;

import com.google.gson.annotations.SerializedName;

public class Attr {

    @SerializedName("offset")
    private Integer Offset;

    @SerializedName("num_res")
    private Integer NumRes;

    @SerializedName("total")
    private Long Total;

    @SerializedName("tag")
    private String Tag;

    @SerializedName("page")
    private Integer Page;

    @SerializedName("perPage")
    private Integer PerPage;

    @SerializedName("totalPages")
    private Integer TotalPages;

    @SerializedName("rank")
    private Integer Rank;

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer rank) {
        Rank = rank;
    }

    public Attr(Integer offset, Integer numRes, Long total, String tag, Integer page, Integer perPage, Integer totalPages, Integer rank) {

        this.Offset = offset;
        this.NumRes = numRes;
        this.Total = total;
        this.Tag = tag;
        this.Page = page;
        this.PerPage = perPage;
        this.TotalPages = totalPages;
        this.Rank = rank;
    }

    public Attr(Integer offset, Integer numRes, Long total, String tag, Integer page, Integer perPage, Integer totalPages) {
        this.Offset = offset;
        this.NumRes = numRes;
        this.Total = total;
        this.Tag = tag;
        this.Page = page;
        this.PerPage = perPage;
        this.TotalPages = totalPages;
    }

    public Integer getOffset() {
        return Offset;
    }

    public void setOffset(Integer offset) {
        Offset = offset;
    }

    public Integer getNumRes() {
        return NumRes;
    }

    public void setNumRes(Integer numRes) {
        NumRes = numRes;
    }

    public Long getTotal() {
        return Total;
    }

    public void setTotal(Long total) {
        Total = total;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public Integer getPage() {
        return Page;
    }

    public void setPage(Integer page) {
        Page = page;
    }

    public Integer getPerPage() {
        return PerPage;
    }

    public void setPerPage(Integer perPage) {
        PerPage = perPage;
    }

    public Integer getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        TotalPages = totalPages;
    }
}
