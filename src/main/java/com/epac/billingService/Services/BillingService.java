package com.epac.billingService.Services;
import com.epac.billingService.Entities.*;
import com.epac.billingService.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
@Service
public class BillingService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrdreRepository ordreRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ProductionPartRepository ProductionPartRepository;
    @Autowired
    private PackagingSlipsRepository Psrepo;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PnlRepository PNLRepository;
    @Autowired
    private   DataBaseSequenceeRespository repo;
    @Autowired
    private ProformaRepositpry proformaRepo;
    public List<Ordre> getOrders (){
    //addClients();
    //NewOrders();
     List<Ordre>orderList=ordreRepository.findAll();
        for (Ordre ordre:orderList){
        if (ordre.isProformaCreated()==false && ordre.isOrdreInvoiced()==false){
            ArrayList l=new ArrayList<Ordre>();
            l.add(ordre);
            createProforma(l);
        }}

        return  orderList;}
    public void UpdateORDERS(Ordre ordre){
          Client client=clientRepository.findClientBySiren(ordre.getClientId());

                   // if (Integer.valueOf(client.getTva())>(0.0015)){
                     //   ordre.setSurcharge(client.getTva());}
                    ordre.setSurcharge(client.getTva());

                        ordre.setTotalCost(new ArrayList<Fees>());

                        Long pID=ordre.getProductionParts().get(0).getProductionPartId();
                    String url = "http://192.168.75.22:7777/SHIPPINGSTATION/packingslipsByproductionPartId/"+pID;
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Accept", "application/json");
                    ResponseEntity<List<PackingSlips>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), new ParameterizedTypeReference<List<PackingSlips>>() {
                    });
                    List<PackingSlips> lisfOfPs= (response.getBody());
                    List<PackingSlips> lisfOfDelivredPs= new ArrayList<>();
                    for (PackingSlips ps:lisfOfPs){
                      if (ps.getStatut().equals("DELIVERED")){
                        lisfOfDelivredPs.add(ps);
                      }
                    }
                     if (ordre.getPackingSlips()==null){
                        ordre.setPackingSlips(lisfOfDelivredPs);
                    }



                /*   for (ProductionPart productionPart :ordre.getProductionParts()) {
                    Collection<Package> packages=productionPart.getPackages();
                       for (Package aPackage : packages) {
                     ordre.setShipAccountName( aPackage.getShipAccountName());
                     ordre.setBillAccountName(aPackage.getBillAccountName());
                     }

                 */




                   }
    public List<PackingSlips> getPs (){
    return Psrepo.findAll();}
    public Ordre gettById(long  id)
    {
        return ordreRepository.findOrderByIdOrder(id);
    }
    public void saveOrUpdateOrder(Ordre order) {
    ordreRepository.save(order);
    }
    public Client findByClientSiren(String siren)  {
        return clientRepository.findClientBySiren(siren);
    }
    public void deleteOrder(Long id)
    {
        ordreRepository.deleteByIdOrder(id);
    }
    public List<ProductionPart> getAllProductionpParts(){
        return ProductionPartRepository.findAll();}
    public List<ProductionPart> getProductionParts(long orderId) { return gettById(orderId).getProductionParts();}
    public List<ProductionPart> affectProductionPart(ProductionPart pr , long id ) {
        gettById(id).getProductionParts().add(pr);
        return gettById(id).getProductionParts();

    }
    public PackingSlips getPackingSlip(long id) {  return Psrepo.findById(id).get();
    }
    public List<PackingSlips> AffetPsToOrder(ArrayList<PackingSlips> ps, Long id) {
       Ordre o= ordreRepository.findOrderByIdOrder(id);
        for(int i=0;i< ps.size();i++) {
            if(o.getPackingSlips()==null){
                List<PackingSlips> packingSlips=new ArrayList<>();
                o.setPackingSlips(packingSlips);
            }
            o.getPackingSlips().add(ps.get(i));
            if(ps.get(i).getOrderIds()==null){
                HashSet<Long> orderIds =new HashSet<>();
                ps.get(i).setOrderIds(orderIds);
            }
            for(ProductionPart pPart:   o.getProductionParts()){
                int Quantity =+ pPart.getQtyDelivered();
                ps.get(i).setQuantity(ps.get(i).getQuantity()+Quantity); }
            ps.get(i).getOrderIds().add(o.getIdOrder());
            ps.get(i).setClientId(o.getClientId());
            ps.get(i).setDestination(o.getDestination());

            if( ps.get(i).getOrderNums()==null){
                HashSet<String> orderNums=new HashSet<>();
                ps.get(i).setOrderNums(orderNums);
            }
            ps.get(i).getOrderNums().add(o.getOrderNum());
            ps.get(i).setBonLivraisonlId(ps.get(i).getBonLivraisonlId()+1);
            ordreRepository.deleteByOrderNum(o.getOrderNum());
            ordreRepository.save(o);
            Psrepo.save(ps.get(i));
        }
        return o.getPackingSlips();
    }
    public  List<PackingSlips> DeletePsFromOrder(long psid,long orderid) {
        Ordre order=gettById(orderid);
        List<PackingSlips> packingSlips= new ArrayList<>();
        for(int i=0;i<order.getPackingSlips().size();i++){
            if(order.getPackingSlips().get(i).getPsId()!=psid){
                packingSlips.add(order.getPackingSlips().get(i));
            }}
        for (PackingSlips ps:order.getPackingSlips()) {
            for(ProductionPart pPart:   order.getProductionParts()){
                int newquantity =ps.getQuantity()-pPart.getQtyDelivered();
                ps.setQuantity(newquantity);
                Psrepo.save(ps);

            }
        }
        order.setPackingSlips( packingSlips);
        ordreRepository.deleteByIdOrder(orderid);
        ordreRepository.save(order);
        PackingSlips ps=getPackingSlip(psid);
        return order.getPackingSlips();
    }
    public Ordre addorder( Ordre ordre){
        Long id =ordre.getIdOrder();
        ListIterator<Ordre> iter =getOrders().listIterator();
        while(iter.hasNext()){
            Ordre  x=(Ordre) iter.next();
            if(x.getOrderNum().equals(ordre.getOrderNum())){
                if (getProforma(id)!=null){
                    proformaRepo.delete(getProforma(id));}
           //     ordreRepository.deleteByOrderNum(x.getOrderNum());
                ordre.setProformaCreated(false);

             //   ArrayList l=new ArrayList<Ordre>();
              //  l.add(ordre);
            //    createProforma(l);
                ordreRepository.save(ordre);
               }}

        return ordre;
    }
    public boolean exist(Ordre o ){
        boolean test=false;
        List<Ordre> orders1= ordreRepository.findAll();
        for (Ordre o1 :orders1){
            if(o.getIdOrder()==o1.getIdOrder()){
                test=true;
                break;
            }
        }
        return test;
    }
    public List<Ordre> NewOrders() {
         String url = "http://192.168.75.22:8057/OrdresForBilling";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        ResponseEntity<List<Ordre>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), new ParameterizedTypeReference<List<Ordre>>() {
        });
        List<Ordre> orders1= ordreRepository.findAll();
        List<Ordre> orders= (response.getBody());
            for (Ordre o :orders){
              UpdateORDERS(o);
              if(orders1.size()==0){
                  UpdateORDERS(o);
                  ordreRepository.save(o);
                }
                else{
                  if(!exist(o)){
                      UpdateORDERS(o);
                        ordreRepository.save(o);
                }
            }
        }
        return orders1;
    }
    public Invoice UpdateInvoice(Invoice invoice3){
        Invoice oldInvoice=invoiceRepository.getInvoiceByIdInvoice(invoice3.getIdInvoice());
        Invoice invoice1 = new Invoice();
        ArrayList<InvoiceLine> invoiceLines=new ArrayList<>();
        invoice1.setSiren(oldInvoice.getSiren());
        invoice1.setInvoiceDate(oldInvoice.getInvoiceDate());
        invoice1.setShipAccountName(oldInvoice.getShipAccountName());
        invoice1.setBillAccountName(oldInvoice.getBillAccountName());
        invoice1.setBillAttn(oldInvoice.getBillAttn());
        invoice1.setBillingAddress(oldInvoice.getBillingAddress());
        invoice1.setBillingCountry(oldInvoice.getBillingCountry());
        invoice1.setBillingLocation(oldInvoice.getBillingLocation());
        invoice1.setShipAttn(oldInvoice.getShipAttn());
        invoice1.setShippingAddress(oldInvoice.getShippingAddress());
        invoice1.setShippingLocation(oldInvoice.getShippingLocation());
        invoice1.setShippingCountry(oldInvoice.getShippingCountry());
        invoice1.setTotal(-oldInvoice.getTotal());
        invoice1.setDueDate(oldInvoice.getDueDate());
        invoice1.setTotalCost(oldInvoice.getTotalCost());
        for(int i=0; i<oldInvoice.getInvoiceLines().size();i++){
            // if(invoice.getInvoiceLines().get(i).isDiffLine()){
            InvoiceLine invoiceLine1= new InvoiceLine();
            invoiceLine1.setBillAttn(oldInvoice.getBillAttn());
            invoiceLine1.setClientPartNum(oldInvoice.getInvoiceLines().get(i).getClientPartNum());
            invoiceLine1.setPoNumber(oldInvoice.getInvoiceLines().get(i).getPoNumber());
            invoiceLine1.setPsIds(oldInvoice.getInvoiceLines().get(i).getPsIds());
            invoiceLine1.setIdOrder(oldInvoice.getInvoiceLines().get(i).getIdOrder());
            invoiceLine1.setIsbn(oldInvoice.getInvoiceLines().get(i).getIsbn());
            invoiceLine1.setIsbn10(oldInvoice.getInvoiceLines().get(i).getIsbn10());
            invoiceLine1.setIsbn13(oldInvoice.getInvoiceLines().get(i).getIsbn13());
           // invoiceLine1.setOemPo(oldInvoice.getInvoiceLines().get(i).getOemPo());
            invoiceLine1.setPartId(oldInvoice.getInvoiceLines().get(i).getPartId());
            invoiceLine1.setShipAttn(oldInvoice.getInvoiceLines().get(i).getShipAttn());
            invoiceLine1.setSurcharge(oldInvoice.getInvoiceLines().get(i).getSurcharge());
            invoiceLine1.setTitle(oldInvoice.getInvoiceLines().get(i).getTitle());
         //   invoiceLine1.setTrackingNumber(oldInvoice.getInvoiceLines().get(i).getTrackingNumber());
            invoiceLine1.setUnitPrice(oldInvoice.getInvoiceLines().get(i).getUnitPrice());
            invoiceLine1.setProductionPage(oldInvoice.getInvoiceLines().get(i).getProductionPage());

            invoiceLine1.setQty(-oldInvoice.getInvoiceLines().get(i).getQty());
            invoiceLine1.setTotal(-oldInvoice.getInvoiceLines().get(i).getTotal());
            invoiceLine1.setType("facturation");
            invoiceLines.add(invoiceLine1);}
        invoice1.setAnnulated(true);
        invoice1.setInvoiceLines(invoiceLines);
        invoice1.setIdInvoice("AP"+oldInvoice.getIdInvoice().substring(2));
        invoice1.setSiren(invoice3.getSiren());
        invoice1.setExported(false);
        invoiceRepository.save(invoice1);
        Invoice invoice=new Invoice();
        float total =0;
       ArrayList<InvoiceLine> invoiceLines3=new ArrayList<>();
       // invoiceLines3.add(invoice3.getInvoiceLines().get(0));
      //  total += invoice3.getInvoiceLines().get(0).getQty() *invoice3.getInvoiceLines().get(0).getUnitPrice();
        for(int i=0; i<invoice3.getInvoiceLines().size();i++){
            if (invoice3.getInvoiceLines().get(i).getType().equals("facturation")) {
                    InvoiceLine newinvoiceLine = new InvoiceLine();
                    newinvoiceLine.setBillAttn(oldInvoice.getBillAttn());
                    newinvoiceLine.setClientPartNum(invoice3.getInvoiceLines().get(i).getClientPartNum());
                    newinvoiceLine.setPoNumber(invoice3.getInvoiceLines().get(i).getPoNumber());
                    newinvoiceLine.setPsIds(invoice3.getInvoiceLines().get(i).getPsIds());
                    newinvoiceLine.setIdOrder(invoice3.getInvoiceLines().get(i).getIdOrder());
                    newinvoiceLine.setIsbn(invoice3.getInvoiceLines().get(i).getIsbn());
                    newinvoiceLine.setIsbn10(invoice3.getInvoiceLines().get(i).getIsbn10());
                    newinvoiceLine.setIsbn13(invoice3.getInvoiceLines().get(i).getIsbn13());
                   // newinvoiceLine.setOemPo(invoice3.getInvoiceLines().get(i).getOemPo());
                newinvoiceLine.setProductionPage(invoice3.getInvoiceLines().get(i).getProductionPage());

                newinvoiceLine.setPartId(invoice3.getInvoiceLines().get(i).getPartId());
                    newinvoiceLine.setBillAttn(oldInvoice.getInvoiceLines().get(i).getShipAttn());
                    newinvoiceLine.setShipAttn(invoice3.getInvoiceLines().get(i).getShipAttn());
                    newinvoiceLine.setSurcharge(invoice3.getInvoiceLines().get(i).getSurcharge());
                    newinvoiceLine.setTitle(invoice3.getInvoiceLines().get(i).getTitle());
                  //  newinvoiceLine.setTrackingNumber(invoice3.getInvoiceLines().get(i).getTrackingNumber());
                    newinvoiceLine.setUnitPrice(invoice3.getInvoiceLines().get(i).getUnitPrice());
                    newinvoiceLine.setQty(invoice3.getInvoiceLines().get(i).getQty());
                    newinvoiceLine.setTotal(newinvoiceLine.getQty() * newinvoiceLine.getUnitPrice());
                    newinvoiceLine.setDiffLine(false);
                  //  invoiceLines3.clear();
                   // total=0;
                    total += newinvoiceLine.getQty() * newinvoiceLine.getUnitPrice();
                   newinvoiceLine.setDiffLine(false);
                   newinvoiceLine.setType("facturation");
                   invoiceLines3.add(newinvoiceLine);}
            if (((Float.valueOf(invoice3.getInvoiceLines().get(i).getSurcharge()))>0) && (invoice3.getInvoiceLines().get(i).getType().equals("facturation"))) {
                if(invoice3.getInvoiceLines().get(i).getType().equals("surcharge)")){
                    invoice3.getInvoiceLines().get(i).setIsbn("Energy surcharge " +invoice3.getInvoiceLines().get(i).getProductionPage()+ "pp " + " @ $" + (Float.valueOf(invoice3.getInvoiceLines().get(i).getSurcharge()) / 100) + "/per page");
                    invoice3.getInvoiceLines().get(i).setQty(invoice3.getInvoiceLines().get(i).getQty());
                    invoice3.getInvoiceLines().get(i).setSurcharge(invoice3.getInvoiceLines().get(i).getSurcharge());
                    invoice3.getInvoiceLines().get(i).setUnitPrice(invoice3.getInvoiceLines().get(i).getProductionPage() * (Float.valueOf(invoice3.getInvoiceLines().get(0).getSurcharge()) / 100));
                    total +=  invoice3.getInvoiceLines().get(i).getUnitPrice() *  invoice3.getInvoiceLines().get(i).getQty();
                    invoice3.getInvoiceLines().get(i).setTotal( invoice3.getInvoiceLines().get(i).getUnitPrice() *  invoice3.getInvoiceLines().get(i).getQty());
                }
                    else{
                    InvoiceLine invoiceLine = new InvoiceLine();
                    invoiceLine.setIsbn("Energy surcharge " +invoice3.getInvoiceLines().get(i).getProductionPage()+ "pp " + " @ $" + (Float.valueOf(invoice3.getInvoiceLines().get(i).getSurcharge()) / 100) + "/per page");
                    invoiceLine.setQty(invoice3.getInvoiceLines().get(i).getQty());
                    invoiceLine.setSurcharge(invoice3.getInvoiceLines().get(i).getSurcharge());
                    invoiceLine.setUnitPrice(invoice3.getInvoiceLines().get(i).getProductionPage() * (Float.valueOf(invoice3.getInvoiceLines().get(0).getSurcharge()) / 100));
                    total += invoiceLine.getUnitPrice() * invoiceLine.getQty();
                    invoiceLine.setTotal(invoiceLine.getUnitPrice() * invoiceLine.getQty());
                    invoiceLine.setDiffLine(true);
                    invoiceLine.setType("surcharge");
                    invoiceLines3.add(invoiceLine);}}
        }
        for( InvoiceLine l: invoice3.getInvoiceLines()){
            if (l.getType().equals("extra")){
                l.setIsbn(l.getName());
                l.setTotal(l.getValue());
                total+=l.getValue();
                invoiceLines3.add(l);
            }
        }
        invoice.setIdInvoice(invoice3.getIdInvoice());
        invoice.setInvoiceDate(invoice3.getInvoiceDate());
        invoice.setDueDate(invoice3.getDueDate());
        invoice.setAccountManager(invoice3.getAccountManager());
        invoice.setAccountName(invoice3.getAccountName());
        invoice.setPnlPrintingNumber(invoice3.getPnlPrintingNumber());
        invoice.setShipAttn(invoice3.getShipAttn());
        invoice.setShippingAddress(invoice3.getShippingAddress());
        invoice.setShippingCountry(invoice3.getShippingCountry());
        invoice.setShippingLocation(invoice3.getShippingLocation());
        invoice.setBillAttn(invoice3.getBillAttn());
        invoice.setBillingAddress(invoice3.getBillingAddress());
        invoice.setBillingCountry(invoice3.getBillingCountry());
        invoice.setBillingLocation(invoice3.getBillingLocation());
        invoice.setStatus(invoice3.getStatus());
        invoice.setSurcharge(invoice3.getSurcharge());
        invoice.setInvoiceLines(invoiceLines3);
        invoice.setTotal(total);
        DatabaseSequence dbs=repo.findById("invoice");
        if( dbs==null ){
            dbs=new DatabaseSequence();
            dbs.setId("invoice");
            dbs.setSeqVal(0);
            repo.save(dbs);
        }
        invoice.setTotalCost(invoice3.getTotalCost());
        invoice.setExported(false);
        invoice.setIdInvoice(("EP"+(dbs.getSeqVal()+1)));
        dbs.setSeqVal(dbs.getSeqVal()+1);
        repo.save(dbs);
        invoice.setSiren(invoice3.getSiren());
        return invoiceRepository.save(invoice);
    }
    public Proforma createProforma(ArrayList<Ordre> orders) {
        Proforma proforma=new Proforma();
        DatabaseSequence dbs=repo.findById("proforma");
        if( dbs==null ){
            dbs=new DatabaseSequence();
            dbs.setId("proforma");
            dbs.setSeqVal(0);
            repo.save(dbs);
        }
        proforma.setIdInvoice("EP"+(((dbs.getSeqVal()+1))));
        dbs.setSeqVal(dbs.getSeqVal()+1);
        repo.save(dbs);
        ArrayList<InvoiceLine> invoiceLines=new ArrayList<>();
        float total =0;
        for(int i=0;i<orders.size();i++){
            InvoiceLine invoiceLine=new InvoiceLine();
            HashSet<Long> dsNumbers = new HashSet<>();
            if(orders.get(i).getPackingSlips()!=null){
            ArrayList<Shipping>shippings =(ArrayList<Shipping>) orders.get(i).getPackingSlips().get(i).getShippings();
            proforma.setSiren((orders.get(i).getClientId()));
            for(int j=0;j<orders.get(i).getPackingSlips().size();j++){
                if(orders.get(i).getPackingSlips().get(j).getPsId()!=null){
                    dsNumbers.add(orders.get(i).getPackingSlips().get(j).getPsId());}}
            proforma.setTotalCost(orders.get(i).getTotalCost());
            Client client=clientRepository.findClientBySiren(orders.get(i).getClientId());
            invoiceLine.setPsIds(dsNumbers);
            invoiceLine.setPnlPrintingNumber(orders.get(i).getProductionParts().get(i).getPnl().getPnlPrintingNumber());
             invoiceLine.setIdOrder(orders.get(i).getIdOrder());
            invoiceLine.setProductionPage(orders.get(i).getProductionParts().get(0).getProductionPage());

                invoiceLine.setClientPartNum(orders.get(i).getClientPoNumber());



                invoiceLine.setPoNumber(orders.get(i).getOrderNum());
            invoiceLine.setUnitPrice((float) orders.get(i).getProductionParts().get(0).getUnitPrice());
              if(client.getSiren().equals("CAI")){
            invoiceLine.setPartId(orders.get(i).getProductionParts().get(0).getClientPartNum());}
            invoiceLine.setQty(orders.get(i).getProductionParts().get(0).getQtyDelivered());
                invoiceLine.setIsbn10(orders.get(i).getProductionParts().get(0).getIsbn10());
                invoiceLine.setIsbn13(shippings.get(i).getBarcode());
            invoiceLine.setTitle(orders.get(i).getProductionParts().get(0).getTitle());
            invoiceLine.setSurcharge(orders.get(i).getSurcharge());
            invoiceLine.setTotal(invoiceLine.getQty()* invoiceLine.getUnitPrice());
            invoiceLine.setType("facturation");
            invoiceLine.setDiffLine(false);
            invoiceLines.add(invoiceLine);
            total+=invoiceLine.getQty()*invoiceLine.getUnitPrice();
            if((Float.valueOf(orders.get(i).getSurcharge()))>0){
                invoiceLine=new InvoiceLine();
                    invoiceLine.setPoNumber(orders.get(i).getOrderNum());
                invoiceLine.setType("surcharge");
                invoiceLine.setValue(Float.valueOf(orders.get(i).getSurcharge()));
                invoiceLine.setName("Surcharge");
                invoiceLine.setIsbn("Energy surcharge "+orders.get(i).getProductionParts().get(0).getProductionPage()+"pp "+" @ $"+(Float.valueOf(orders.get(i).getSurcharge())/100)+"/per page");
                invoiceLine.setQty(orders.get(i).getProductionParts().get(0).getQtyDelivered());
                invoiceLine.setSurcharge(orders.get(i).getSurcharge());
                invoiceLine.setUnitPrice((orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100)));
                total+=(invoiceLine.getQty()* orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100));
                invoiceLine.setTotal(invoiceLine.getQty()* orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100));
                invoiceLine.setDiffLine(true);
                invoiceLines.add(invoiceLine);
            }
            if (orders.get(i).getTotalCost().size()!=0){
                for (int j=0;j<orders.get(i).getTotalCost().size();j++){
                    InvoiceLine il = new InvoiceLine();
                    il.setName(orders.get(i).getTotalCost().get(j).getType());
                    il.setType("extra");
                    il.setValue(Float.parseFloat(orders.get(i).getTotalCost().get(j).getValue()));
                    il.setIsbn(orders.get(i).getTotalCost().get(j).getType() );
                    il.setIdOrder(orders.get(i).getIdOrder());
                    il.setTotal(Float.parseFloat(orders.get(i).getTotalCost().get(j).getValue()));
                    total += il.getTotal();
                    il.setDiffLine(false);
                    invoiceLines.add(il);}}
        Client client1=clientRepository.findClientBySiren(orders.get(0).getClientId());
        proforma.setInvoiceLines(invoiceLines);
                if(shippings.get(i).getDestination()!=null)
                if(shippings.get(i).getBillAttn()!=null)
                    proforma.setAccountManager(client1.getAccountManager());
                proforma.setAccountName(client1.getAccountName());
        proforma.setBillAttn(shippings.get(i).getBillAttn());
        if(shippings.get(i).getShipAttn()!=null)
         proforma.setShipAttn(shippings.get(i).getShipAttn());
        if(shippings.get(i).getShipAccountName()!=null)
        proforma.setShipAccountName(shippings.get(i).getShipAccountName());
        if(shippings.get(i).getBillAccountName()!=null)
            proforma.setBillAccountName(shippings.get(i).getBillAccountName());
        if(shippings.get(i).getBillingAddress()!=null)
            proforma.setBillingAddress(shippings.get(i).getBillingAddress());
        if(client1.getShippingAdress()!=null)
            proforma.setShippingAddress(client1.getShippingAdress());
        if(shippings.get(i).getShippingLocation()!=null)
            proforma.setShippingLocation(shippings.get(i).getShippingLocation());
        if(shippings.get(i).getBillingLocation()!=null)
            proforma.setBillingLocation(shippings.get(i).getBillingLocation());
        if(shippings.get(i).getShippingCountry()!=null)
            proforma.setShippingCountry(shippings.get(i).getShippingCountry());
        if(shippings.get(i).getBillingCountry()!=null)
            proforma.setBillingCountry(shippings.get(i).getBillingCountry());
        proforma.setTotal(total);
        proforma.setExported(false);
        Date date = new Date(System.currentTimeMillis());
        proforma.setInvoiceDate(date);
                Instant instant=new Date().toInstant();
                if (proforma.getSiren().equals("SAV")){
                    Instant nextDays=instant.plus(45, ChronoUnit.DAYS);
                    proforma.setDueDate(Date.from(nextDays));
                }
                else {
                    Instant nextDays=instant.plus(30, ChronoUnit.DAYS);
                    proforma.setDueDate(Date.from(nextDays));

                    ;}
        //orders.get(i).setInvoiceDelay(d);
        orders.get(i).setProformaCreated(true);
                System.out.println(orders.get(i).isProformaCreated());
        ordreRepository.save(orders.get(i));
        proformaRepo.save(proforma);
    }}
      return proforma;
    }
    public Invoice GenerateEnMasse(ArrayList<Ordre> orders) {
        Invoice invoice=new Invoice();
        DatabaseSequence dbs=repo.findById("invoice");
        if( dbs==null ){
            dbs=new DatabaseSequence();
            dbs.setId("invoice");
            dbs.setSeqVal(0);
            repo.save(dbs);
        }

        invoice.setIdInvoice("EP"+((dbs.getSeqVal()+1)));
        dbs.setSeqVal(dbs.getSeqVal()+1);
        repo.save(dbs);

        //System.out.println(invoice.getIdInvoice());
        ArrayList<InvoiceLine> invoiceLines=new ArrayList<>();
        float total =0;
        for(int i=0;i<orders.size();i++){
            InvoiceLine invoiceLine=new InvoiceLine();
            HashSet<Long> dsNumbers = new HashSet<>();
            for(int j=0;j<orders.get(i).getPackingSlips().size();j++){
                if(orders.get(i).getPackingSlips().get(j).getPsId()!=null){
                    dsNumbers.add(orders.get(i).getPackingSlips().get(j).getPsId());}
            }
            if(orders.get(i).getPackingSlips()!=null){
            ArrayList<Shipping>shippings =(ArrayList<Shipping>) orders.get(i).getPackingSlips().get(0).getShippings();

            for(int index=0;index<shippings.size();index++ )    {
                invoice.setSiren((orders.get(i).getClientId()));
            Client client=clientRepository.findClientBySiren(orders.get(i).getClientId());
            invoiceLine.setPsIds(dsNumbers);
            invoiceLine.setIdOrder(orders.get(i).getIdOrder());
            invoiceLine.setBillAttn(orders.get(i).getBillAttn());
            invoice.setTotalCost(orders.get(i).getTotalCost());
            invoiceLine.setProductionPage(orders.get(i).getProductionParts().get(0).getProductionPage());
            invoiceLine.setPnlPrintingNumber(orders.get(i).getProductionParts().get(0).getPnl().getPnlPrintingNumber());
            invoiceLine.setClientPartNum(orders.get(i).getClientPoNumber());
            invoiceLine.setPoNumber(orders.get(i).getOrderNum());
          //  invoiceLine.setOemPo(orders.get(i).getProductionParts().get(i).getPartid());
            invoiceLine.setUnitPrice((float) orders.get(i).getProductionParts().get(0).getUnitPrice());
                if(client.getSiren().equals("CAI")){
                    invoiceLine.setPartId(orders.get(i).getProductionParts().get(0).getClientPartNum());}
            invoiceLine.setQty(orders.get(i).getProductionParts().get(0).getQtyDelivered());
            invoiceLine.setIsbn10(shippings.get(index).getIsbn10());
            invoiceLine.setIsbn13(shippings.get(index).getBarcode());
            invoiceLine.setTitle(orders.get(i).getProductionParts().get(0).getTitle());
            invoiceLine.setSurcharge(orders.get(i).getSurcharge());
            invoiceLine.setTotal(invoiceLine.getQty()* invoiceLine.getUnitPrice());
            invoiceLine.setDiffLine(true);
            invoiceLine.setType("facturation");
            invoiceLines.add(invoiceLine);
            total+=invoiceLine.getQty()*invoiceLine.getUnitPrice();
            if((Float.valueOf(orders.get(i).getSurcharge()))>0){
                invoiceLine=new InvoiceLine();
                    invoiceLine.setPoNumber(orders.get(i).getOrderNum());
                invoiceLine.setType("surcharge");
                invoiceLine.setValue(Float.valueOf(orders.get(i).getSurcharge()));
                invoiceLine.setName("Surcharge");
                invoiceLine.setIsbn("Energy surcharge "+orders.get(i).getProductionParts().get(0).getProductionPage()+"pp "+" @ $"+(Float.valueOf(orders.get(i).getSurcharge())/100)+"/per page");
                invoiceLine.setQty(orders.get(i).getProductionParts().get(0).getQtyDelivered());
                invoiceLine.setSurcharge(orders.get(i).getSurcharge());
                invoiceLine.setUnitPrice((orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100)));
                total+=(invoiceLine.getQty()* orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100));
                invoiceLine.setTotal(invoiceLine.getQty()* orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100));
                invoiceLine.setDiffLine(true);
                invoiceLines.add(invoiceLine);
            }
            if (orders.get(i).getTotalCost().size()!=0){
                for (int j=0;j<orders.get(i).getTotalCost().size();j++){
                    InvoiceLine il = new InvoiceLine();
                    il.setName(orders.get(i).getTotalCost().get(j).getType());
                    il.setType("extra");
                    il.setValue(Float.parseFloat(orders.get(i).getTotalCost().get(j).getValue()));
                    il.setIsbn(orders.get(i).getTotalCost().get(j).getType() );
                    il.setIdOrder(orders.get(i).getIdOrder());
                    il.setTotal(Float.parseFloat(orders.get(i).getTotalCost().get(j).getValue()));
                    total += il.getTotal();
                    il.setDiffLine(false);
                    invoiceLines.add(il);}}
        orders.get(i).setProformaCreated(true);
        orders.get(i).setOrdreInvoiced(true);
        Client client1=clientRepository.findClientBySiren(orders.get(i).getClientId());
        invoice.setInvoiceLines(invoiceLines);
         invoice.setAccountManager(client1.getAccountManager());
         invoice.setAccountName(client1.getAccountName());

          if(shippings.get(index).getDestination()!=null)
                if(shippings.get(index).getBillAttn()!=null)
                    invoice.setBillAttn(shippings.get(index).getBillAttn());
            if(shippings.get(index).getShipAttn()!=null)
                invoice.setShipAttn(shippings.get(index).getShipAttn());
            if(shippings.get(index).getShipAccountName()!=null)
                invoice.setShipAccountName(shippings.get(index).getShipAccountName());
            if(shippings.get(index).getBillAccountName()!=null)
                invoice.setBillAccountName(shippings.get(index).getBillAccountName());
            if(shippings.get(index).getBillingAddress()!=null)
                invoice.setBillingAddress(shippings.get(index).getBillingAddress());
            if(client1.getShippingAdress()!=null)
                invoice.setShippingAddress(client1.getShippingAdress());
            if(shippings.get(index).getShippingLocation()!=null)
                invoice.setShippingLocation(shippings.get(index).getShippingLocation());
            if(shippings.get(index).getBillingLocation()!=null)
                invoice.setBillingLocation(shippings.get(index).getBillingLocation());
            if(shippings.get(index).getShippingCountry()!=null)
                invoice.setShippingCountry(shippings.get(index).getShippingCountry());
            if(shippings.get(index).getBillingCountry()!=null)
                invoice.setBillingCountry(shippings.get(index).getBillingCountry());
                ordreRepository.save(orders.get(i));
                proformaRepo.delete(getProforma(orders.get(i).getIdOrder()));

            }}}

        invoice.setTotal(total);
        invoice.setExported(false);
        Date date = new Date(System.currentTimeMillis());

        invoice.setInvoiceDate(date);

        Calendar c = Calendar.getInstance();
        c.setTime(invoice.getInvoiceDate());
        if (invoice.getSiren().equals("SAV")){
            c.add(Calendar.DATE, 45);
            Date d = c.getTime();
            invoice.setDueDate(d);
        }
        else {
            c.add(Calendar.DATE, 30);
            Date d = c.getTime();
            invoice.setDueDate(d);}
        invoiceRepository.save(invoice);





        return invoice;
    }
    /*public Invoice save(Invoice invoice) {
        invoice.setIdInvoice(invoiceRepository.findAll().size()+2);
        invoiceRepository.save(invoice);
        return invoice;
    }

     */
    public Invoice createInvoice(ArrayList<Ordre> listOrders) {
        HashSet<String> listClient=new HashSet<>();
        for(int i=0;i<listOrders.size();i++){
            listClient.add(listOrders.get(i).getClientId());
        }
        for(String b:listClient){
            ArrayList<Ordre> orders=new ArrayList<>();
            float total=0;
           /* DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            df.setMinimumFractionDigits(2);
            df.setDecimalSeparatorAlwaysShown(false);

            */
            for(int k=0;k<listOrders.size();k++){
                if(listOrders.get(k).getClientId().equals(b)){
                    orders.add(listOrders.get(k));}}
            Invoice invoice=new Invoice();
            invoiceRepository.delete(invoice);
            DatabaseSequence dbs=repo.findById("invoice");
            if( dbs==null ){
                dbs=new DatabaseSequence();
                dbs.setId("invoice");
                dbs.setSeqVal(0);
                repo.save(dbs);
            }
            invoice.setIdInvoice("EP"+((dbs.getSeqVal()+1)));
            dbs.setSeqVal(dbs.getSeqVal()+1);
            repo.save(dbs);
            /*if(invoiceSequenceRepoistory.findAll()==null){
                InvoiceSequence invoiceSequence= new InvoiceSequence();
                invoiceSequence.setIndex(1);
                invoiceSequenceRepoistory.save(invoiceSequence);
            }*/
           // List<InvoiceSequence> invoiceSequence= invoiceSequenceRepoistory.findAll();

            ArrayList<InvoiceLine> invoiceLines=new ArrayList<>();
            for(int i=0;i<orders.size();i++){
                InvoiceLine invoiceLine=new InvoiceLine();
                HashSet<Long> dsNumbers = new HashSet<>();
                if(orders.get(i).getPackingSlips()==null){
                    List<PackingSlips> packingSlips=new ArrayList<>();
                    orders.get(i).setPackingSlips(packingSlips);
                }
                for(int j=0;j<orders.get(i).getPackingSlips().size();j++){
                    if(orders.get(i).getPackingSlips().get(j).getPsId()!=null){
                        dsNumbers.add(orders.get(i).getPackingSlips().get(j).getPsId());}
                }


                Client client=clientRepository.findClientBySiren(orders.get(i).getClientId());
                invoiceLine.setPsIds(dsNumbers);
                invoiceLine.setIdOrder(orders.get(i).getIdOrder());
                invoiceLine.setBillAttn(orders.get(i).getBillAttn());
              invoiceLine.setPnlPrintingNumber(orders.get(i).getProductionParts().get(i).getPnl().getPnlPrintingNumber());

              if (orders.get(i).isUseClientPartNumberINVOICE()==true)  {
                    invoiceLine.setClientPartNum(orders.get(i).getClientPoNumber());

                }
                ordreRepository.deleteByIdOrder(orders.get(i).getIdOrder());
                orders.get(i).setProformaCreated(true);
                ordreRepository.save(orders.get(i));
                invoice.setShipAccountName((orders.get(i).getBillAccountName()));
              invoice.setBillAccountName(orders.get(i).getShipAccountName());

                    invoiceLine.setPoNumber(orders.get(i).getOrderNum());
            //    invoiceLine.setOemPo(orders.get(i).getProductionParts().get(i).getPartid());
                invoiceLine.setUnitPrice((float) orders.get(i).getProductionParts().get(0).getUnitPrice());
                if(client.getSiren().equals("CAI")){
                    invoiceLine.setPartId(orders.get(i).getProductionParts().get(0).getClientPartNum());}
                invoiceLine.setQty(orders.get(i).getProductionParts().get(0).getQtyDelivered());
                    invoiceLine.setIsbn10(orders.get(i).getProductionParts().get(0).getIsbn10());
                    invoiceLine.setIsbn13(orders.get(i).getProductionParts().get(0).getBarcode());
                invoiceLine.setTitle(orders.get(i).getProductionParts().get(0).getTitle());
                invoiceLine.setSurcharge(orders.get(i).getSurcharge());
                invoiceLine.setTotal(invoiceLine.getQty()* invoiceLine.getUnitPrice());
                invoiceLines.add(invoiceLine);
                total+=invoiceLine.getQty()*invoiceLine.getUnitPrice();
                if((Float.valueOf(orders.get(i).getSurcharge()))>0){
                    invoiceLine=new InvoiceLine();
                    invoiceLine.setIdOrder(orders.get(i).getIdOrder());
                        invoiceLine.setPoNumber(orders.get(i).getOrderNum());
                    invoiceLine.setIsbn("Energy surcharge "+orders.get(i).getProductionParts().get(0).getProductionPage()+"pp "+" @ $"+(Float.valueOf(orders.get(i).getSurcharge())/100)+"/per page");
                    invoiceLine.setQty(orders.get(i).getProductionParts().get(0).getQtyDelivered());
                    invoiceLine.setUnitPrice((orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100)));
                    total+=(invoiceLine.getQty()* orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100));
                    invoiceLine.setTotal(invoiceLine.getQty()* orders.get(i).getProductionParts().get(0).getProductionPage()*(Float.valueOf(orders.get(i).getSurcharge())/100));
                    invoiceLines.add(invoiceLine);

                }
            }
            Client client1=clientRepository.findClientBySiren(orders.get(0).getClientId());
            invoice.setInvoiceLines(invoiceLines);
            invoice.setShipAttn(orders.get(0).getShipAttn());
            invoice.setAccountManager(client1.getAccountManager());
            invoice.setAccountName(client1.getAccountName());
            if(orders.get(0).getBillAttn()!=null)
                invoice.setBillAttn(orders.get(0).getBillAttn());
          if(orders.get(0).getShipAccountName()!=null)
            invoice.setShipAccountName(orders.get(0).getShipAccountName());
          if(orders.get(0).getBillAccountName()!=null)
            invoice.setBillAccountName(orders.get(0).getBillAccountName());
            if(client1.getBillingAdress()!=null)
                invoice.setBillingAddress(client1.getBillingAdress());
            if(client1.getShippingAdress()!=null)
                invoice.setShippingAddress(client1.getShippingAdress());
            if(client1.getShippingLocation()!=null)
                invoice.setShippingLocation(client1.getShippingLocation());
            if(client1.getBillingLocation()!=null)
                invoice.setBillingLocation(client1.getBillingLocation());
            if(client1.getShippingCountry()!=null)
                invoice.setShippingCountry(client1.getShippingCountry());
            if(client1.getBillingCountry()!=null)
                invoice.setBillingCountry(client1.getBillingCountry());
            invoice.setTotal(total);
            Date date = new Date(System.currentTimeMillis());
            invoice.setInvoiceDate(date);
            invoiceRepository.save(invoice);
        }
        return null;
    }
    public List<Invoice> getInvoices(){
        return invoiceRepository.findAll();}
    public List<Proforma> getallProforma(){

        return proformaRepo.findAll() ;}
    public List<Invoice> getallInvoices(){

    return invoiceRepository.findAll() ;}
    public List<PackingSlips> PsByOrder( Long id) {
        Ordre o = ordreRepository.findOrderByIdOrder(id);
        Long pID=o.getProductionParts().get(0).getProductionPartId();

        String url = "http://192.168.75.22:7777/SHIPPINGSTATION/packingslipsByproductionPartId/"+pID;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        ResponseEntity<List<PackingSlips>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), new ParameterizedTypeReference<List<PackingSlips>>() {
        });
        List<PackingSlips> lisfOfPs= (response.getBody());
        o.setPackingSlips(lisfOfPs);
        List<PackingSlips> lisfOfPs1 = null;
        return lisfOfPs;
    }
    public Invoice validateProforma( Proforma proforma){
        List <InvoiceLine> invoiceLines=proforma.getInvoiceLines();
        for (InvoiceLine invoiceLine:invoiceLines){
            if (invoiceLine.getIdOrder()!=0){
                Ordre o=ordreRepository.findOrderByIdOrder(invoiceLine.getIdOrder());
                ordreRepository.deleteByIdOrder(o.getIdOrder());
                o.setOrdreInvoiced(true);
                ordreRepository.save(o);
        }}
        Invoice invoice =new Invoice();
        DatabaseSequence dbs=repo.findById("invoice");
        if( dbs==null ){
            dbs=new DatabaseSequence();
            dbs.setId("invoice");
            dbs.setSeqVal(0);
            repo.save(dbs);
        }
        invoice.setTotalCost(proforma.getTotalCost());
        invoice.setAccountManager(proforma.getAccountManager());
        invoice.setAccountName(proforma.getAccountName());
        invoice.setSiren((proforma.getSiren()));
        invoice.setIdInvoice("EP"+((dbs.getSeqVal()+1)));
        dbs.setSeqVal(dbs.getSeqVal()+1);
        repo.save(dbs);
        invoice.setInvoiceLines(proforma.getInvoiceLines());
        invoice.setBillAttn(proforma.getBillAttn());
        invoice.setShipAttn(proforma.getShipAttn());
        invoice.setShipAccountName(proforma.getShipAccountName());
        invoice.setBillAccountName(proforma.getBillAccountName());
        invoice.setBillingAddress(proforma.getBillingAddress());
        invoice.setShippingAddress(proforma.getShippingAddress());
        invoice.setShippingLocation(proforma.getShippingLocation());
        invoice.setBillingLocation(proforma.getBillingLocation());
        invoice.setShippingCountry(proforma.getShippingCountry());
        invoice.setBillingCountry(proforma.getBillingCountry());
        Date date = new Date(System.currentTimeMillis());
        invoice.setInvoiceDate(date);
        if (invoice.getSiren().equals("SAV")){
            Instant instant=invoice.getInvoiceDate().toInstant();
            Instant NextDays=instant.plus(45, ChronoUnit.DAYS);

            invoice.setDueDate(Date.from(NextDays));        }
        else {
            Instant instant=invoice.getInvoiceDate().toInstant();
            Instant NextDays=instant.plus(30, ChronoUnit.DAYS);

            invoice.setDueDate(Date.from(NextDays));

            ;}
        invoice.setExported(false);
        invoice.setTotal(proforma.getTotal());
        invoiceRepository.save(invoice);
        proformaRepo.delete(proforma);
        return invoice;
    }
    public  Proforma deleteInvoice(int idInvoice){
        Proforma proforma =proformaRepo.getProformaByIdInvoice(Integer.toString(idInvoice));
        List <InvoiceLine> invoiceLines=proforma.getInvoiceLines();
        for (InvoiceLine invoiceLine:invoiceLines){
            if (invoiceLine.getIdOrder()!=0){
                Ordre o=ordreRepository.findOrderByIdOrder(invoiceLine.getIdOrder());
                ordreRepository.deleteByIdOrder(o.getIdOrder());
                o.setProformaCreated(false);
                ordreRepository.save(o);
        }   }
        proformaRepo.delete(proforma);
        return  proforma;
    }
    public List<Invoice> getInvoicesByDate1(Date date1 , Date date2 ){
        ArrayList<Invoice> invoices=new ArrayList<>();
           for (Invoice invoice :getallInvoices()){
               DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               if ((formatter.format(invoice.getInvoiceDate()).compareTo(formatter.format(date1))>=0) &&(formatter.format(invoice.getInvoiceDate()).compareTo(formatter.format(date2))<=0)){
                   invoices.add(invoice);
               }
           }

      return invoices ;

    }
    public List<Invoice> getInvoicesByDate2(Date date1 , Date date2 , String client ){
        ArrayList<Invoice> invoices=new ArrayList<>();
        for (Invoice invoice :getallInvoices()){
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if ((formatter.format(invoice.getInvoiceDate()).compareTo(formatter.format(date1))>=0) &&(formatter.format(invoice.getInvoiceDate()).compareTo(formatter.format(date2))<=0)&& (invoice.getSiren().equals(client))){
                invoices.add(invoice);
            }
        }

        return invoices ;

    }
    public List<String> getClientsName(){

       List<String> clientsName=new ArrayList<>();
//        for (int i=0 ; i<getallInvoices().size();i++){
//            if (ClientsName.contains(getallInvoices().get(i).getSiren())==false){
//            ClientsName.add(getallInvoices().get(i).getSiren());
//        }}
//        return ClientsName;
        for(HashMap name:invoiceRepository.getClientList()){
            clientsName.add((String) name.get("client"));

        }
        return clientsName;
    }
    public Invoice editeInvoice(Invoice invoice){
        Invoice invoice2 = new Invoice();
        ArrayList<InvoiceLine> invoiceLines=new ArrayList<>();
        for(int i=0; i<invoice.getInvoiceLines().size();i++){
            InvoiceLine invoiceLine1= new InvoiceLine();
            invoiceLine1.setBillAttn(invoice.getInvoiceLines().get(i).getBillAttn());
            invoiceLine1.setClientPartNum(invoice.getInvoiceLines().get(i).getClientPartNum());
            invoiceLine1.setPoNumber(invoice.getInvoiceLines().get(i).getPoNumber());
            invoiceLine1.setPsIds(invoice.getInvoiceLines().get(i).getPsIds());
            invoiceLine1.setIdOrder(invoice.getInvoiceLines().get(i).getIdOrder());
            invoiceLine1.setIsbn(invoice.getInvoiceLines().get(i).getIsbn());
            invoiceLine1.setIsbn10(invoice.getInvoiceLines().get(i).getIsbn10());
            invoiceLine1.setIsbn13(invoice.getInvoiceLines().get(i).getIsbn13());
           // invoiceLine1.setOemPo(invoice.getInvoiceLines().get(i).getOemPo());
            invoiceLine1.setPartId(invoice.getInvoiceLines().get(i).getPartId());
            invoiceLine1.setShipAttn(invoice.getInvoiceLines().get(i).getShipAttn());
            invoiceLine1.setSurcharge(invoice.getInvoiceLines().get(i).getSurcharge());
            invoiceLine1.setTitle(invoice.getInvoiceLines().get(i).getTitle());
        //    invoiceLine1.setTrackingNumber(invoice.getInvoiceLines().get(i).getTrackingNumber());
            invoiceLine1.setUnitPrice(invoice.getInvoiceLines().get(i).getUnitPrice());
            invoiceLine1.setQty(invoice.getInvoiceLines().get(i).getQty());
            invoiceLine1.setTotal(invoice.getInvoiceLines().get(i).getTotal());
            invoiceLines.add(invoiceLine1);
        }
        invoice2.setAccountManager(invoice.getAccountManager());
        invoice2.setAccountName(invoice.getAccountName());
        invoice2.setIdInvoice(invoice.getIdInvoice());
        invoice2.setInvoiceDate(invoice.getInvoiceDate());
        invoice2.setDueDate(invoice.getDueDate());
        invoice2.setShipAccountName(invoice.getShipAccountName());
        invoice2.setBillAccountName((invoice.getBillAccountName()));
        invoice2.setPnlPrintingNumber(invoice.getPnlPrintingNumber());
        invoice2.setShipAttn(invoice.getShipAttn());
        invoice2.setShippingAddress(invoice.getShippingAddress());
        invoice2.setShippingCountry(invoice.getShippingCountry());
        invoice2.setShippingLocation(invoice.getShippingLocation());
        invoice2.setBillAttn(invoice.getBillAttn());
        invoice2.setBillingAddress(invoice.getBillingAddress());
        invoice2.setBillingCountry(invoice.getBillingCountry());
        invoice2.setBillingLocation(invoice.getBillingLocation());
        invoice2.setStatus(invoice.getStatus());
        invoice2.setSurcharge(invoice.getSurcharge());
        invoice2.setInvoiceLines(invoiceLines);
        invoice2.setTotal(0.0F);
        invoice2.setExported(invoice.isExported());
        return invoice2;
    }
    public List<Ordre>getArchivedOrdres(){
        List<Ordre> ORDRES = new ArrayList<>();
        for (Ordre o : getOrders()) {
            if (o.isOrdreInvoiced()==true) {
                ORDRES.add(o);
            }
        }
        return ORDRES ;}
    public List<HashMap> getnbInvoicebyClient() {
//
//        Map<String, Integer> list = new HashMap<String, Integer>();
//        ArrayList<String> aList = new ArrayList<String>();
//        List<Invoice> invoices = getInvoices();
//        for (int i=0; i<invoices.size();i++) {
//            aList.add(getInvoices().get(i).getAccountName());
//            int k = Collections.frequency(aList,aList.get(i));
//            list.put(aList.get(i),k);
//        }
//        return  list;
        return invoiceRepository.getInvoicesByAllClient();
    }
    public Proforma getProforma(Long id) {
      List<Proforma>ll=  proformaRepo.findAll();
      for (Proforma p:ll) {
         if  ( p.getInvoiceLines().get(0).getIdOrder()==id){
             return p;
         }
      }
      return null;
    }
    public void  exportInvoices(List<String> idInvoice) {
         for (String s : idInvoice){
             Invoice invoice=invoiceRepository.getInvoiceByIdInvoice(s);
             invoice.setExported(true);
             invoiceRepository.save(invoice);
         }

    }
    public boolean existClient(Client c){
        boolean test=false;
        List<Client> clients1 = clientRepository.findAll();
        for (Client c1:clients1){
            if((c.getSiren()).equals(c1.getSiren())){
                test=true;
                break;
            }
        }
        return test;
    }
    public ArrayList<Client> addClients(){
        String url = "http://192.168.75.22:8011/ClientsForBilling/true";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        ResponseEntity<ArrayList<Client>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(headers), new ParameterizedTypeReference<ArrayList<Client>>() {
        });
        ArrayList<Client> clients= (response.getBody());
        List<Client> clients1= clientRepository.findAll();
        for (Client c :clients){
            if(clients1.size()==0){
                clientRepository.save(c);
            }
            else{
                if(!existClient(c)){
                    clientRepository.save(c);
                }
            }
        }
       return clients;
    }
    public Ordre newOrder(ArrayList<Ordre> ordre){
        for (Ordre ordreId:ordre){
            ordreRepository.save(ordreId);
        }
     return  ordreRepository.save(ordre.get(0));
    }

  public void addNewClients(ArrayList<Client> clients) {
      clientRepository.saveAll(clients);
  }
}



