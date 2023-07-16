describe('template spec', () => {
  it('passes', () => {
     cy.fixture('blogdetails.json').as("blogsJSON");
     cy.intercept('http://localhost:4200/home',"@blogsJSON").as('getblogdetails');
    cy.request('http://localhost:4200/home');
    cy.wait('@getblogdetails');
  })
})