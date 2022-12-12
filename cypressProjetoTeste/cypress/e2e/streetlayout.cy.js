describe('Street layout tests', () => {
  it('POST /streetlayout/create - create a street layout', () => {
    const streetLayout = {
      street: "Z9",
      picking: "98",
      level: 10
    }
    cy.POST_StreetLayout('create', streetLayout)
      .then((response)=>{
        expect(response.status).to.equal(201)
        expect(response).to.have.property("registrationDate")
        expect(response).to.have.property("id")
        expect(response).to.have.property("street")
        expect(response).to.have.property("picking")
        expect(response).to.have.property("level")
        expect(response).to.have.property("pallet")

        expect(response.body.street).to.equal(streetLayout.street)
        expect(response.body.picking).to.equal(streetLayout.picking)
        expect(response.body.level).to.equal(streetLayout.level)

      })
  })
})