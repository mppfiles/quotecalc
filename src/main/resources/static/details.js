var app = new Vue({
      el: '#app',
      vuetify: new Vuetify(),
      methods: {
          async getQuoteDetails() {
            let response = await axios.get("/quote/details");
            this.$data.quote = response.data;
            this.$data.loading = false;
          },

          back() {
            window.location.href = "index.html";
          }
      },
      data: {
      loading: true,
        quote: {
            term: null,
            loanAmount: null,
            interestRate: null,
            residualValue: null,
            approxQuote: null
        }
      },
      created() {
        this.getQuoteDetails();
      }
    });