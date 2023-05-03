package com.epac.billingService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pnl {
   private Boolean pnlExluded;
    private int pnlPrintingNumber;
    private int pnlYear;

    public Pnl() {
    }

  public Boolean getPnlExluded() {
    return pnlExluded;
  }

  public void setPnlExluded(Boolean pnlExluded) {
    this.pnlExluded = pnlExluded;
  }

  public int getPnlYear() {
        return pnlYear;
    }

    public void setPnlYear(int pnlYear) {
        this.pnlYear = pnlYear;
    }

    public int getPnlPrintingNumber() {
        return pnlPrintingNumber;
    }

    public void setPnlPrintingNumber(int pnlPrintingNumber) {
        this.pnlPrintingNumber = pnlPrintingNumber;
    }
}
