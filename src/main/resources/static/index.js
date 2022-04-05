var app = new Vue({
      el: '#app',
      vuetify: new Vuetify(),
      methods: {
          async getQuote() {
            let response = await axios.get("/quote", { params: this.$data.quote });
            this.$data.quote.approxQuote = response.data.approxQuote;
          },

          async submit() {
            try {
                let response = await axios.post("/quote", this.$data.quote, {"Content-Type": "application/json"});
                this.$data.quote = response.data;

                if(this.$data.quote.quoteAmountExceeded) {
                    window.location.href = '/max.html';
                } else {
                    window.location.href = '/details.html';
                }

            } catch(err) {
                alert("Some error occurred!");
            }
          },
      },
      data: {
        quote: {
            term: 36,
            loanAmount: 100000,
            interestRate: 0.1,
            residualValue: 0,
            approxQuote: null,
            realQuote: null,
            quoteAmountExceeded: false
        }
      }
    });