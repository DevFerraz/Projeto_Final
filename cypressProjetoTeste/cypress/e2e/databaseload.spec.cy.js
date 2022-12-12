describe('Creating a databaseload', () => {
    it('Cria algumas posições em streetLayout', () => {
       
       
        for(let j = 65; j<87; j++){
           let street = String.fromCharCode(j)
                for(let x = 1; x<=30; x++){
                    let picking = x.toString();
                    for(let l = 0; l<=5; l++){
                        let streetLayout = {
                            "street": street,
                            "picking": picking,
                            "level": l
                        }
                        cy.POST_StreetLayout('create', streetLayout).then((resp)=>{
                            expect(resp.status).to.equal(201)
                        })
                    }
                }
            }            
                     
    })
  })