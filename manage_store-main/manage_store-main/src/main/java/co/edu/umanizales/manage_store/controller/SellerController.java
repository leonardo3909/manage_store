package co.edu.umanizales.manage_store.controller;

import co.edu.umanizales.manage_store.controller.dto.ResponseDTO;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getSellers()
    {

        return new ResponseEntity<>(new ResponseDTO(
                200,sellerService.getSellers(),null
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createSeller(@RequestBody Seller seller) {
        Seller findStore = sellerService.getSellerById(seller.getCode());
        if( findStore == null) {
            sellerService.addSeller(seller);
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Vendedor agregado", null
            ), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO(
                    409, "Ya existe un vendedor con ese código", null
            ), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<ResponseDTO> getSellerById(@PathVariable String code)
    {
        Seller findStore= sellerService.getSellerById(code);
        if(findStore == null)
        {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "No existe un vendedor con ese código", null
            ), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, findStore, null
            ), HttpStatus.OK);
        }
    }
}
