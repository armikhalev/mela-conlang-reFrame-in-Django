import hmac, hashlib, os, subprocess

from rest_framework import generics

from rest_framework.decorators import api_view
from django.http import HttpResponse, HttpResponseForbidden, HttpResponseBadRequest
from django.views.decorators.csrf import csrf_exempt

from .models import Koyla, Card, Intro, GrammarCard, Alphabet
from .serializers import KoylaSerializer, CardSerializer, IntroSerializer, GrammarCardSerializer, AlphabetSerializer

SECRET = os.environ.get("GITHUB_WEBHOOK_SECRET", "9cbd312a9f8a4f76b2b2f9ff0e1c55d1")

# English words
class WordSet(generics.ListAPIView):

	serializer_class = KoylaSerializer

	def get_queryset(self):
		queryset = Koyla.objects.all()
		letter = self.request.query_params.get('letter', None)
		if letter is not None:
			queryset = queryset.filter(word__startswith=letter)

		return queryset

# Mela words
class LaSet(generics.ListAPIView):

	serializer_class = KoylaSerializer

	def get_queryset(self):
		queryset = Koyla.objects.all()
		la = self.request.query_params.get('letter', None)
		if la is not None:
			queryset = queryset.filter(la__startswith=la)

		return queryset

# Cards for building blocks view
class CardSet(generics.ListAPIView):

	serializer_class = CardSerializer

	def get_queryset(self):
		queryset = Card.objects.all()
		return queryset

class IntroSet(generics.ListAPIView):
	serializer_class = IntroSerializer

	def get_queryset(self):
		queryset = Intro.objects.all()
		return queryset

class GrammarCardSet(generics.ListAPIView):
	serializer_class = GrammarCardSerializer

	def get_queryset(self):
		queryset = GrammarCard.objects.all()
		id = self.request.query_params.get('id', None)
		if id is not None:
			queryset = queryset.filter(id=id)
		return queryset
	
class GrammarCardDetail(generics.RetrieveUpdateDestroyAPIView):
	queryset = GrammarCard.objects.all()
	serializer_class = GrammarCardSerializer

class AlphabetSet(generics.ListAPIView):
	serializer_class = AlphabetSerializer

	def get_queryset(self):
		queryset = Alphabet.objects.all()
		return queryset

def _valid_sig(request):
	sig = request.headers.get("X-Hub-Signature-256", "")
	if not sig.startswith("sha256="):
		return False
	mac = hmac.new(SECRET.encode(), request.body, hashlib.sha256).hexdigest()
	return hmac.compare_digest(sig.split("=",1)[1], mac)

@csrf_exempt
def github_webhook(request):
    if request.method != "POST":
        return HttpResponseBadRequest("POST only")
    if not _valid_sig(request):
        return HttpResponseForbidden("bad signature")

    subprocess.check_call(["/home/Melasi/melasi_backend/mela-conlang-reFrame-in-Django/deploy.sh"])
    return HttpResponse("OK\n")
