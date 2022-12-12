package com.nft.marketplace.nftmarketplace.models.reuqests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarketRequest {
    int pageNumber;
    int limit;
}
