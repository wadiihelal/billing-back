package com.epac.billingService.Controllers;
import com.epac.billingService.Entities.*;
import com.epac.billingService.Repositories.*;
import com.epac.billingService.Services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
public class Controllers {
    @Autowired
    private BillingService service ;
    @Autowired
    private OrdreRepository ordreRepository;
    @Autowired
    private ProductionPartRepository productionPartRepository;
    @Autowired
    private ClientRepository ClientRepo;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private PackagingSlipsRepository PsRepo;
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/OrdersForBilling",method= RequestMethod.GET)
    public List<Ordre> getOrders (){
        return service.getOrders();
    }
  @CrossOrigin(origins = "*")
  @PostMapping(value="/newOrder")
  public Ordre newOrder (@RequestBody ArrayList<Ordre> ordre){
    return service.newOrder(ordre);
  }
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/NewOrders",method= RequestMethod.GET)
    public List<Ordre> NewOrders (){
        System.out.println("list"+service.NewOrders());
        return service.NewOrders();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/Order/{Id}",method= RequestMethod.GET)
    public Ordre getOrder (@PathVariable("Id") final long Id){
      //  System.out.println(service.gettById(orderId).get().getIdOrder());
        return service.gettById(Id);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/ProductionPartOrder/{orderId}",method= RequestMethod.GET)
    public List<ProductionPart> getProductionParts(@PathVariable  long orderId)
    {
        return service.getProductionParts(orderId);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/AllProductionParts")
    public List<ProductionPart> getAllProductionParts (){
        return service.getAllProductionpParts();
    }
    @CrossOrigin("*")
    @PostMapping("/addOrder")
    public Ordre addorder(@RequestBody Ordre ordre)
    {
        return service.addorder(ordre);}
    @CrossOrigin("*")
    @DeleteMapping("/delete/{id}")
    public Ordre deleteorder(@PathVariable Long id)
    { service.deleteOrder(id);
        return null;}
    @CrossOrigin("*")
    @GetMapping("/addManyorders")
    public List<Ordre> addManyorders() {
      return  service.NewOrders();
    }
    @CrossOrigin("*")
    @PostMapping("/addProductionPart")
    public ProductionPart addProductionPart(@RequestBody ProductionPart pr)
    {return  productionPartRepository.save(pr);}
    @CrossOrigin("*")
    @RequestMapping(value="/UpdateOrder",method= RequestMethod.POST)
    public void UpdateOrder(@RequestBody Ordre ordre)
    {
             service.saveOrUpdateOrder(ordre);
    }
    @CrossOrigin("*")
    @RequestMapping(value="/AffectProductionPart/{id}",method= RequestMethod.POST)
    public List<ProductionPart> affectProductionPartToOrder(@RequestBody ProductionPart pr ,@PathVariable long id){
        return service.affectProductionPart(pr,id);
    }
    @CrossOrigin("*")
    @PostMapping("/addPs")
    public PackingSlips addPs(@RequestBody PackingSlips ps)
    {   Date date = new Date(System.currentTimeMillis());
        ps.setCreationDate(date);

        return
            PsRepo.save(ps);}
    @CrossOrigin(origins = "*")
    @GetMapping("/AllPs")
    public List<PackingSlips> getAllPs(){
        return service.getPs();  }
    @CrossOrigin(origins = "*")
    @PostMapping("/AffectPsToOrder/{orderId}")
    public List<PackingSlips> affectPsToOder(@PathVariable("orderId") final long orderId,@RequestBody ArrayList<PackingSlips> ps)
    {
        return service. AffetPsToOrder(ps,orderId);  }
    @CrossOrigin(origins = "*")
    @GetMapping("/psByOrder/{idorder}")
    public List<PackingSlips> PsByOrder(@PathVariable long idorder )
    { return service.PsByOrder(idorder);  }
    @CrossOrigin(origins = "*")
    @PostMapping("/createInvoice")
    public Proforma createInvoice(@RequestBody ArrayList<Ordre> ordres) {
        return service.createProforma(ordres);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/createInvoiceEnMasse")
    public Invoice GenerateEnMasse(@RequestBody ArrayList<Ordre> ordres) {
        return service.GenerateEnMasse(ordres);
    }
  //  @CrossOrigin(origins = "*")
 //   @PostMapping("/createInvoiceTest")
 //   public Invoice createInvoiceTest(@RequestBody ArrayList<Ordre> ordres) {
       // return service.createProforma(ordres);
   // }
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/allInvoices",method= RequestMethod.GET)
    public List<Invoice> getInvoices (){
        return service.getallInvoices();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/allProforma",method= RequestMethod.GET)
    public List<Proforma> getallProforma (){
        return service.getallProforma();
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/validateInvoice")
    public Invoice validateInvoice(@RequestBody Proforma  proforma){
        return service.validateProforma(proforma);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteProforma/{idInvoice}")
    public Proforma deleteProforma(@PathVariable int idInvoice){
      return   service.deleteInvoice(idInvoice);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/invoicesByDate/{from}/{to}")
    public List<Invoice> invoiceBydate(@PathVariable(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from, @PathVariable(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to){
      return service.getInvoicesByDate1(from,to);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/invoicesByClientAndDate/{from}/{to}/{client}")
    public List invoiceBydateAndClient(@PathVariable(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from, @PathVariable(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to,@PathVariable String client){
        return service.getInvoicesByDate2(from,to,client);}
    @CrossOrigin(origins = "*")
    @GetMapping("/NbInvoicesByClient")
    public List<HashMap> nbInvoicebyClient (){
        return service.getnbInvoicebyClient();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/ArchivedOrdres")
    public List<Ordre> getArchivedOrdres (){
        return service.getArchivedOrdres();
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/annulationInvoice")
    public Invoice annulateInvoice(@RequestBody Invoice invoice)
    {
        return  service.UpdateInvoice(invoice);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/editInvoice")
    public Invoice editInvoice(@RequestBody Invoice invoice)
    {
        return  service.editeInvoice(invoice);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/proforma/{idOrdre}")
    public Proforma getProforma(@PathVariable Long idOrdre)
    {
        return  service.getProforma(idOrdre);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/ClientsNames")
    public List<String>ClientsNames()

    {
        return  service.getClientsName();
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/deletePsFromOrder/{idps}/{idorder}")
    public List<PackingSlips> deletePs(@PathVariable long idps, @PathVariable long idorder )
    {  return service.DeletePsFromOrder(idps,idorder);  }
    @CrossOrigin(origins = "*")
    @PostMapping("/exportInvoices")
    public void exportInvoices(@RequestBody List<String> idInvoice)
    {service.exportInvoices(idInvoice);
    }
     @PostMapping("/addManyClients")
    public void addNewClient(@RequestBody ArrayList<Client> clients)
     {service.addNewClients(clients);
       }

}

